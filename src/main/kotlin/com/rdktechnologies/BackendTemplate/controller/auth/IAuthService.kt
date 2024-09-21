package com.rdktechnologies.BackendTemplate.controller.auth

import com.rdktechnologies.BackendTemplate.dto.request.LoginRequest
import com.rdktechnologies.BackendTemplate.dto.request.RegisterRequest
import com.rdktechnologies.BackendTemplate.dto.response.ApiResponse
import org.springframework.http.ResponseEntity

interface IAuthService {

    fun registerUser(request: RegisterRequest) : ResponseEntity<ApiResponse>
    fun login(request: LoginRequest): ResponseEntity<ApiResponse>
    fun getAllUsers(): ResponseEntity<ApiResponse>

}