package com.tomas.membership

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.containers.MariaDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest
class MembershipApiApplicationTests {

    companion object {

        @Container
        @ServiceConnection
        val mariaDB = MariaDBContainer("mariadb:lts")
            .withDatabaseName("membership")
            .withUsername("membership_user")
            .withPassword("membership_password")
    }

    @Test
    fun contextLoads() {
    }
}