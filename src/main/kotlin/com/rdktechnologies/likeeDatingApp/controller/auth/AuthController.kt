package com.rdktechnologies.likeeDatingApp.controller.auth

import com.rdktechnologies.likeeDatingApp.controller.auth.IAuthService
import com.rdktechnologies.likeeDatingApp.dto.request.AuthRequest
import com.rdktechnologies.likeeDatingApp.dto.request.OtpRequest
import com.rdktechnologies.likeeDatingApp.dto.response.ApiResponse
import com.rdktechnologies.likeeDatingApp.util.autowired
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/auth/")
@CrossOrigin("*")
@Tag(name = "Authentication")
class AuthController {

    val iAuthService: IAuthService by autowired()

    @PostMapping("/")
    @CrossOrigin("*")
    fun auth(@Valid @RequestBody request: AuthRequest): ResponseEntity<ApiResponse> {
        return iAuthService.getAllUsers()
    }

    @PostMapping("/otp")
    @CrossOrigin("*")
    fun otp(@Valid @RequestBody request: OtpRequest): ResponseEntity<ApiResponse> {
        return iAuthService.getAllUsers()
    }

}