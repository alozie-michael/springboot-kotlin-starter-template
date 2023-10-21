package com.base.repository

import com.base.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface RoleReposity : JpaRepository<Role, Long> {
    fun findByName(name: String): Optional<Role>

    fun findByNameIn(names: List<String>): List<Role>
}