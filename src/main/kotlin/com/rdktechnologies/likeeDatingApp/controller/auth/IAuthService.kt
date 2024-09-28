package com.rdktechnologies.likeeDatingApp.controller.auth

import com.rdktechnologies.likeeDatingApp.dto.request.AuthRequest
import com.rdktechnologies.likeeDatingApp.dto.request.OtpRequest
import com.rdktechnologies.likeeDatingApp.dto.response.ApiResponse
import org.springframework.http.ResponseEntity

interface IAuthService {

//    fun registerUser(request: OtpRequest) : ResponseEntity<ApiResponse>
//    fun login(request: AuthRequest): ResponseEntity<ApiResponse>
    fun getAllUsers(): ResponseEntity<ApiResponse>

}