package com.base

import com.base.model.BaseUserDto
import com.base.model.BaseUserDtoPage
import com.base.entity.Role
import com.base.entity.User
import java.util.UUID

class TestUtils {
    companion object {
        val USER_ID: UUID = UUID.fromString("00000000-0000-0000-0000-000000001111")
        val USER_ID_TO_DELETE: UUID = UUID.fromString("00000000-0000-0000-0000-000000001113")
        fun createUserDto() = BaseUserDto (
            name = "Test User",
            email = "m@gmail.com",
            password = "xxx",
            roles = listOf("ROLE_ADMIN"),
            active = true
        )

        fun createUser() = User (
            null,
            userId = USER_ID,
            name = "Test User",
            email = "m@gmail.com",
            password = "xxx",
            roles = roles(),
            active = true
        )

        fun createUserDtoPage() = BaseUserDtoPage (
            pageSize = 10,
            page = 1,
            data = listOf(createUserDto()),
            totalPages = 1,
            totalElements = 3
        )

        private fun roles() = listOf(Role(1, "ROLE_ADMIN"))
    }
}
