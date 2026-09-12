package com.tomas.membership.infrastructure.persistence.repository

import com.tomas.membership.infrastructure.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataUserRepository : JpaRepository<UserEntity, Long> {

    fun existsByEmail(email: String): Boolean
}