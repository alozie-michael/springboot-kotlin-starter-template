package com.base.repository

import com.base.entity.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String, pageable: Pageable): Page<User>
    fun findByUserId(userId: UUID): Optional<User>
}