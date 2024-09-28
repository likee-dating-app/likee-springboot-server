package com.rdktechnologies.likeeDatingApp.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class OtpRequest(

    @field:NotEmpty(message = "Email is mandatory")
    @field:NotBlank(message = "Email is mandatory")
    @field:NotNull(message = "Email is mandatory")
    val email: String? = null,

    @field:NotNull(message = "OTP is mandatory")
    @field:Pattern(regexp = "\\d{6}", message = "OTP must be a 6-digit number")
    val otp: String? = null

)
