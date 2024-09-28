package com.rdktechnologies.likeeDatingApp.controller.auth

import com.rdktechnologies.likeeDatingApp.dto.request.LoginRequest
import com.rdktechnologies.likeeDatingApp.dto.request.RegisterRequest
import com.rdktechnologies.likeeDatingApp.dto.response.ApiResponse
import org.springframework.http.ResponseEntity

interface IAuthService {

    fun registerUser(request: RegisterRequest) : ResponseEntity<ApiResponse>
    fun login(request: LoginRequest): ResponseEntity<ApiResponse>
    fun getAllUsers(): ResponseEntity<ApiResponse>

}