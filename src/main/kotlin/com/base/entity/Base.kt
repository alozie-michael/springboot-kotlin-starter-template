package com.base.entity

import jakarta.persistence.*
import lombok.Data

@Data
open class Base(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open val id: Int?,

    @Version
    open val version: Int? = null
)
