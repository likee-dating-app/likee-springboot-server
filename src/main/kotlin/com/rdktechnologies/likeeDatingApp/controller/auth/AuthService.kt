package com.rdktechnologies.likeeDatingApp.controller.auth

import com.rdktechnologies.likeeDatingApp.constant.Constants.ACCOUNT_IS_CREATED
import com.rdktechnologies.likeeDatingApp.constant.Constants.LOGIN_SUCCESSFULLY
import com.rdktechnologies.likeeDatingApp.constant.Constants.USERNAME_OR_PASSWORD_IS_INCORRECT
import com.rdktechnologies.likeeDatingApp.dto.request.LoginRequest
import com.rdktechnologies.likeeDatingApp.dto.request.RegisterRequest
import com.rdktechnologies.likeeDatingApp.dto.response.ApiResponse
import com.rdktechnologies.likeeDatingApp.repository.role.Role
import com.rdktechnologies.likeeDatingApp.repository.token.Token
import com.rdktechnologies.likeeDatingApp.repository.token.TokenRepository
import com.rdktechnologies.likeeDatingApp.repository.token.TokenType
import com.rdktechnologies.likeeDatingApp.repository.user.UserRepository
import com.rdktechnologies.likeeDatingApp.repository.user.Users
import com.rdktechnologies.likeeDatingApp.security.JwtService
import com.rdktechnologies.likeeDatingApp.util.HelperUtil.detailsWithTokens
import com.rdktechnologies.likeeDatingApp.util.autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService : IAuthService {

    val userRepository: UserRepository by autowired()
    val bCryptPasswordEncoder: BCryptPasswordEncoder by autowired()
    val tokenRepository: TokenRepository by autowired()
    val jwtService: JwtService by autowired()
    val authenticationManager: AuthenticationManager by autowired()

    override fun registerUser(request: RegisterRequest): ResponseEntity<ApiResponse> {

        // Encrypt the password once, and simplify user creation
        val encryptedPassword = bCryptPasswordEncoder.encode(request.password)

        val newUser = Users(
            firstName = request.firstName,
            middleName = request.middleName,
            lastName = request.lastName,
            email = request.email,
            keypassword = encryptedPassword, // Directly use encrypted password
            role = Role.USER
        )

        // Save the new user and handle response
        val savedUser = userRepository.save(newUser)

        // Build and return response
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(
                ApiResponse(
                    statusCode = HttpStatus.CREATED.value(),
                    message = ACCOUNT_IS_CREATED,
                    data = savedUser    // Return saved user data
                )
            )
    }



    @Transactional
    override fun login(request: LoginRequest): ResponseEntity<ApiResponse> {

        try {
            val mAuthManager = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    request.email?.trim(),
                    request.password?.trim()
                )
            )

            if (mAuthManager.isAuthenticated) {
                val user = userRepository.findByEmail(request.email?:"").orElseThrow { UsernameNotFoundException("User not found") }
                val jwtToken = jwtService.generateToken(user)
                val refreshToken = jwtService.generateRefreshToken(user)
                revokeAllUserTokens(user)
                saveUserToken(user, jwtToken)
                return ResponseEntity.status(HttpStatus.OK)
                    .body(
                        ApiResponse(
                            error = false,
                            statusCode = HttpStatus.OK.value(),
                            message = LOGIN_SUCCESSFULLY,
                            serverError = "",
                            data = user.detailsWithTokens(jwtToken, refreshToken)
                        )
                    )
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                        ApiResponse(
                            error = true,
                            statusCode = HttpStatus.BAD_REQUEST.value(),
                            message = USERNAME_OR_PASSWORD_IS_INCORRECT,
                            serverError = ""
                        )
                    )
            }
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                    ApiResponse(
                        error = true,
                        statusCode = HttpStatus.BAD_REQUEST.value(),
                        message = USERNAME_OR_PASSWORD_IS_INCORRECT,
                        serverError = e.message.toString()
                    )
                )
        }
    }

    override fun getAllUsers(): ResponseEntity<ApiResponse> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(
                ApiResponse(
                    error = false,
                    statusCode = HttpStatus.OK.value(),
                    message = "",
                    serverError = "",
                    data = userRepository.findAll()
                )
            )
    }


    private fun saveUserToken(user: Users, jwtToken: String) {
        val token = Token(
            users = user,
            token = jwtToken,
            tokenType = TokenType.BEARER,
            expired = false,
            revoked = false
        )
        tokenRepository.save(token)
    }

    @Async
    fun revokeAllUserTokens(user: Users) {
        user.id?.let { tokenRepository.deleteByUsers_Id(it) }
    }
}