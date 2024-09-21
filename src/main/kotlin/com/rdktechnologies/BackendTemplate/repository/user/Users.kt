package com.rdktechnologies.BackendTemplate.repository.user


import com.fasterxml.jackson.annotation.JsonIgnore
import com.rdktechnologies.BackendTemplate.repository.role.Role
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.time.Instant
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp

@Entity(name = "users")
@Table(name = "users",
    uniqueConstraints = [UniqueConstraint(columnNames = ["loginId"])])
@Component
data class Users(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    var firstName: String? = null,
    var middleName: String? = null,
    var lastName: String? = null,
    var profilePic: String? = null,
    var email: String? = null,

    @Column(name = "password")
    @JsonIgnore
    var keypassword: String? = null,

    @Enumerated(EnumType.STRING)
    val role: Role? = Role.USER,

    @CreationTimestamp
    var createdAt: Instant? = null,

    @UpdateTimestamp
    var updatedAt: Instant? = null,

    @Column(columnDefinition = "boolean default true")
    @JsonIgnore
    var accountNonExpired: Boolean = true,

    @Column(columnDefinition = "boolean default true")
    @JsonIgnore
    var accountNonLocked: Boolean = true,

    @Column(columnDefinition = "boolean default true")
    @JsonIgnore
    var credentialsNonExpired: Boolean = true,

    @Column(columnDefinition = "boolean default true")
    @JsonIgnore
    var enabled: Boolean = true,

    @Column(columnDefinition = "boolean default false")
    @JsonIgnore
    var isDeleted: Boolean = false

) : UserDetails {

    @JsonIgnore
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return role?.authorities ?: mutableListOf()
    }

    @JsonIgnore
    override fun getPassword(): String {
        return keypassword ?: ""
    }

    @JsonIgnore
    override fun getUsername(): String {
        return email ?: ""
    }

    @JsonIgnore
    override fun isAccountNonExpired() = accountNonExpired

    @JsonIgnore
    override fun isAccountNonLocked() = accountNonLocked

    @JsonIgnore
    override fun isCredentialsNonExpired() = credentialsNonExpired

    @JsonIgnore
    override fun isEnabled() = enabled
}
