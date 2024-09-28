package com.rdktechnologies.likeeDatingApp.util

import com.rdktechnologies.likeeDatingApp.dto.model.UserWithToken
import com.rdktechnologies.likeeDatingApp.repository.user.Users

object HelperUtil {

    fun Users.detailsWithTokens(jwtToken: String, refreshToken: String): UserWithToken {
        return UserWithToken(
            accessToken = jwtToken,
            refreshToken = refreshToken,
            userData =this
        )
    }
}