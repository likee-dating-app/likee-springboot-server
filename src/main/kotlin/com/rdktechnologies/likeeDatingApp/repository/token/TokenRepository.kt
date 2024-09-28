package com.rdktechnologies.likeeDatingApp.repository.token

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Repository
interface TokenRepository : JpaRepository<Token, Long> {
    fun findByToken(token: String): Optional<Token>

    fun deleteByUsers_Id(userId: Long)

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM token WHERE user_id =:userId", nativeQuery = true)
    fun deleteTokensByUserId(userId: Long?)

}
