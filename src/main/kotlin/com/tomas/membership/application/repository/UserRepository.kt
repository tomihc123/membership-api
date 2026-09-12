package com.tomas.membership.application.repository

import com.tomas.membership.domain.model.User

interface UserRepository {

    fun save(user: User): User

    fun findById(id: Long): User?

    fun existsByEmail(email: String): Boolean

    fun findAll(): List<User>

}