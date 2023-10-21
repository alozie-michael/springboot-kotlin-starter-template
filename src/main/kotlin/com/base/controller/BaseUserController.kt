package com.base.controller

import com.base.api.BaseUserApi
import com.base.model.BaseIdResponse
import com.base.model.BaseUserDto
import com.base.model.BaseUserDtoPage
import com.base.service.UserService
import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/v1")
class BaseUserController(val userService: UserService) : BaseUserApi {
    override fun createBaseUser(baseUserDto: BaseUserDto): ResponseEntity<BaseIdResponse> {
        return ResponseEntity.status(201).body(userService.createBaseUser(baseUserDto))
    }

    override fun deleteBaseUserById(id: UUID): ResponseEntity<Unit> {
        return ResponseEntity.status(204).body(userService.deleteUser(id))
    }

    override fun getBaseUserById(id: UUID): ResponseEntity<BaseUserDto> {
        return ResponseEntity.ok(userService.getUser(id))
    }

    override fun getBaseUsers(page: Int, pageSize: Int, email: String?): ResponseEntity<BaseUserDtoPage> {
        return ResponseEntity.ok(userService.getUsers(email, Pageable.ofSize(pageSize).withPage(page)))
    }
}