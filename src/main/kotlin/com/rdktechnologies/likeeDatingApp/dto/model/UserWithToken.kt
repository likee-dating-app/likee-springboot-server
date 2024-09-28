package com.rdktechnologies.likeeDatingApp.dto.model

import com.rdktechnologies.likeeDatingApp.repository.user.Users

data class UserWithToken(
    val accessToken:String?=null,
    val refreshToken:String?=null,
    val userData:Users?=null,
)
