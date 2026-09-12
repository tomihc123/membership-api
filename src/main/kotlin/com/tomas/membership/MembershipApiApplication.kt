package com.tomas.membership

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MembershipApiApplication

fun main(args: Array<String>) {
    runApplication<MembershipApiApplication>(*args)
}