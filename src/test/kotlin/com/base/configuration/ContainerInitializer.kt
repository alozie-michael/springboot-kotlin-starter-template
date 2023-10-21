package com.base.configuration

import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

class ContainerInitializer private constructor() : PostgreSQLContainer<ContainerInitializer>(DockerImageName.parse("postgres:13-alpine")) {

    override fun start() {
        super.start()
        System.setProperty("DB_URL", container?.jdbcUrl ?: "")
        System.setProperty("DB_USERNAME", container?.username ?: "")
        System.setProperty("DB_PASSWORD", container?.password ?: "")
    }

    override fun stop() {
        //do nothing, JVM handles shut down
    }

    companion object {

        @Volatile
        private var container: ContainerInitializer? = null

        fun getInstance() = container ?: synchronized(this) {
            container ?: ContainerInitializer().also { container = it }
        }
    }
}