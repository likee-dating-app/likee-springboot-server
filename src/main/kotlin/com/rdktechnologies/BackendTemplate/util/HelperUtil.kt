package com.rdktechnologies.BackendTemplate.util

import com.rdktechnologies.BackendTemplate.dto.model.UserWithToken
import com.rdktechnologies.BackendTemplate.repository.user.Users
import org.apache.catalina.User

object HelperUtil {

    fun Users.detailsWithTokens(jwtToken: String, refreshToken: String): UserWithToken {
        return UserWithToken(
            accessToken = jwtToken,
            refreshToken = refreshToken,
            userData =this
        )
    }
}