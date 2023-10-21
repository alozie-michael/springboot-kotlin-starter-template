package com.base.service

import com.base.entity.Role
import com.base.repository.RoleReposity
import org.springframework.stereotype.Service

@Service
class RoleService(val roleRepository: RoleReposity) {

    fun getRoles(roles: List<String>?): List<Role> {
        if(roles.isNullOrEmpty()) return mutableListOf()
        return roleRepository.findByNameIn(roles)
    }

    fun getStrRoles(roles: List<Role>): List<String> {
        if(roles.isEmpty()) return mutableListOf()
        return roles.map { it.name }
    }
}
