package com.base.repository

import com.base.TestUtils
import com.base.configuration.ContainerInitializer
import com.base.configuration.DatabaseInitializer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest: DatabaseInitializer() {

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun findByEmail() {
        val userPage = userRepository.findByEmail("m@gmail.com", Pageable.ofSize(10))
        assertTrue(userPage.content.isNotEmpty())
    }

    @Test
    fun findByUserId() {
        val userId = TestUtils.USER_ID
        val user = userRepository.findByUserId(userId)
        assertEquals(userId, user.get().userId)
    }
}