package com.base.mapper

import com.base.model.BaseIdResponse
import com.base.model.BaseUserDto
import com.base.model.BaseUserDtoPage
import com.base.entity.User
import com.base.service.RoleService
import com.base.service.UserService
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import org.springframework.data.domain.Page

@Mapper(componentModel = "spring",
    uses = [RoleService::class])
interface UserMapper {
    companion object {
        @JvmField
        val INSTANCE: UserMapper = Mappers.getMapper(UserMapper::class.java)
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", expression = "java(java.util.UUID.randomUUID())")
    fun mapToUser(userDto: BaseUserDto): User

    @Mapping(target = "password", defaultValue = "*****")
    fun mapToUserDto(user: User): BaseUserDto

    @Mapping(target = "id", source = "userId")
    fun mapToUserDtoId(user: User): BaseIdResponse

    @Mapping(target = "pageSize", source = "size")
    @Mapping(target = "page", source = "number")
    @Mapping(target = "data", source = "content", defaultExpression = "java(List.of())")
    fun mapPageData(userPage: Page<User>): BaseUserDtoPage

}