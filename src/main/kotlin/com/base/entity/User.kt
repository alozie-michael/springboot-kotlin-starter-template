package com.base.entity

import jakarta.persistence.*
import lombok.Data
import lombok.NoArgsConstructor
import java.util.UUID

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val userId: UUID,
    var name: String,
    var email: String,
    var password: String,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")], inverseJoinColumns = [JoinColumn(name = "role_id")] )
    var roles: List<Role> = mutableListOf(),
    var active: Boolean
)
