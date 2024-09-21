package com.rdktechnologies.BackendTemplate.dto.model

import com.rdktechnologies.BackendTemplate.repository.user.Users

data class UserWithToken(
    val accessToken:String?=null,
    val refreshToken:String?=null,
    val userData:Users?=null,
)
