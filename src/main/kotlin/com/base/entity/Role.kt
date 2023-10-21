package com.base.entity

import jakarta.persistence.*
import lombok.NoArgsConstructor

@Entity
@Table(name = "roles")
@NoArgsConstructor
data class Role (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val name: String
)
