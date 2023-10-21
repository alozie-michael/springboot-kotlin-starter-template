package com.base.repository

import com.base.configuration.DatabaseInitializer
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RolesRepositoryTest: DatabaseInitializer() {

    @Autowired
    lateinit var roleRepository: RoleReposity

    @Test
    fun findByName() {
        val optionalRole = roleRepository.findByName("ROLE_USER")
        assertTrue(optionalRole.isPresent)
    }

    @Test
    fun findByNameIn() {
        val roles = roleRepository.findByNameIn(listOf("ROLE_USER", "ROLE_ADMIN"))
        assertTrue(roles.size >= 2)
    }
}