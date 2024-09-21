package com.rdktechnologies.BackendTemplate.repository.user

import com.rdktechnologies.BackendTemplate.repository.role.Role
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<Users, Long> {
    // Find user by email
    fun findByEmail(email: String):Optional<Users>

    // Check if user exists by email
    fun existsByEmail(email: String): Boolean

    // Custom query to delete a user by email (if needed)
    @Query("DELETE FROM users u WHERE u.email = :email")
    fun deleteByEmail(@Param("email") email: String): Int
}
