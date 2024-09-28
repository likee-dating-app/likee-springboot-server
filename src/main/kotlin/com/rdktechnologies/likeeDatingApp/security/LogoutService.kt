package com.rdktechnologies.likeeDatingApp.security

import com.rdktechnologies.likeeDatingApp.repository.token.Token
import com.rdktechnologies.likeeDatingApp.repository.token.TokenRepository
import com.rdktechnologies.likeeDatingApp.util.autowired
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.LogoutHandler
import org.springframework.stereotype.Service

@Service
class LogoutService : LogoutHandler {

    val tokenRepository: TokenRepository by autowired()

    override fun logout(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val authHeader: String = request.getHeader("Authorization")
        if (!authHeader.startsWith("Bearer ")) {
            return
        }
        val jwt: String = authHeader.substring(7)
        val storedToken: Token = tokenRepository.findByToken(jwt)
            .orElse(null)

        storedToken.expired = true
        storedToken.revoked = true
        tokenRepository.save(storedToken)
        SecurityContextHolder.clearContext()
    }
}
