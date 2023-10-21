package com.base.service

import com.base.exception.ResourceNotFoundException
import com.base.model.BaseIdResponse
import com.base.model.BaseUserDto
import com.base.model.BaseUserDtoPage
import com.base.entity.User
import com.base.mapper.UserMapper
import com.base.repository.UserRepository
import mu.KLogging
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService (
    val userMapper: UserMapper,
    val userRepository: UserRepository) {
    companion object : KLogging()

    fun createBaseUser(baseUserDto: BaseUserDto) : BaseIdResponse {
        logger.info { "creating base user ${baseUserDto.email}" }
        val user = userMapper.mapToUser(baseUserDto)
        return userMapper.mapToUserDtoId(userRepository.save(user))
    }

    fun getUsers(email: String?, pageable: Pageable) : BaseUserDtoPage {
        logger.info { "getting users" }
        val userPage = email?.let{ userRepository.findByEmail(it, pageable) } ?: userRepository.findAll(pageable)
        return userMapper.mapPageData(userPage)
    }

    fun getUser(userId: UUID) : BaseUserDto {
        logger.info { "getting user $userId" }
        return userMapper.mapToUserDto(findById(userId))
    }

    fun deleteUser(userId: UUID) {
        logger.info { "deleting user $userId" }
        userRepository.delete(findById(userId))
    }

    private fun findById(userId: UUID) : User = userRepository.findByUserId(userId)
        .orElseThrow{ ResourceNotFoundException("User $userId Not Found")}
}