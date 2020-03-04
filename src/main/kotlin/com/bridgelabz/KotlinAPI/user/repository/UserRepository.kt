package com.bridgelabz.KotlinAPI.user.repository

import com.bridgelabz.KotlinAPI.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository : JpaRepository<User?, Any?> {
    // For finding or recovering the user account by MailId
    fun findByEmail(email: String?): User?

    fun findById(id: String?): User?
}