package com.rdktechnologies.BackendTemplate.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class RegisterRequest(

    @field:NotEmpty(message = "Email is mandatory")
    @field:NotBlank(message = "Email is mandatory")
    @field:NotNull(message = "Email is mandatory")
    val email: String? = null,

    @field:NotEmpty(message = "Password is mandatory")
    @field:NotBlank(message = "Password is mandatory")
    @field:NotNull(message = "Password is mandatory")
    val password: String? = null,

    // Required fields
    @field:NotEmpty(message = "First name is mandatory")
    @field:NotBlank(message = "First name is mandatory")
    @field:NotNull(message = "First name is mandatory")
    val firstName: String? = null,

    @field:NotEmpty(message = "Last name is mandatory")
    @field:NotBlank(message = "Last name is mandatory")
    @field:NotNull(message = "Last name is mandatory")
    val lastName: String? = null,

    // Optional field
    val middleName: String? = null
)
