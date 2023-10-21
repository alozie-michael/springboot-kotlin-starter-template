package com.base

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BaseServiceApplication

fun main(args: Array<String>) {
    runApplication<com.base.BaseServiceApplication>(*args)
}
