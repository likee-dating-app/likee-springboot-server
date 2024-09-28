package com.rdktechnologies.likeeDatingApp.repository.token

import com.rdktechnologies.likeeDatingApp.repository.user.Users
import jakarta.persistence.*
import org.springframework.stereotype.Component

@Entity
@Component
data class Token(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @Column(unique = true)
    var token: String? = null,

    @Enumerated(EnumType.STRING)
    var tokenType: TokenType = TokenType.BEARER,
    var revoked: Boolean = false,
    var expired: Boolean = false,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var users: Users? = null
)
