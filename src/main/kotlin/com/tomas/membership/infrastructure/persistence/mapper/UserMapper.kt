package com.tomas.membership.infrastructure.persistence.mapper

import com.tomas.membership.domain.model.User
import com.tomas.membership.infrastructure.persistence.entity.UserEntity
import java.time.LocalDateTime

object UserMapper {

    fun toEntity(user: User): UserEntity {
        return UserEntity(
            id = user.id,
            email = user.email,
            firstName = user.firstName,
            lastName = user.lastName,
            createdAt = user.createdAt ?: LocalDateTime.now()
        )
    }

    fun toDomain(entity: UserEntity): User {
        return User(
            id = entity.id,
            email = entity.email,
            firstName = entity.firstName,
            lastName = entity.lastName,
            createdAt = entity.createdAt
        )
    }
}