package com.rdktechnologies.BackendTemplate.controller.auth

import com.rdktechnologies.BackendTemplate.dto.request.LoginRequest
import com.rdktechnologies.BackendTemplate.dto.request.RegisterRequest
import com.rdktechnologies.BackendTemplate.dto.response.ApiResponse
import com.rdktechnologies.BackendTemplate.util.autowired
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
@Tag(name = "Authentication")
class AuthController {

    val iAuthService: IAuthService by autowired()

    @PostMapping("/login")
    @CrossOrigin("*")
    fun login(@Valid @RequestBody request: LoginRequest): ResponseEntity<ApiResponse> {
        return iAuthService.login(request)
    }

    @PutMapping("/register")
    @CrossOrigin("*")
    fun register(@Valid @RequestBody request: RegisterRequest): ResponseEntity<ApiResponse> {
        return iAuthService.registerUser(request)
    }

}