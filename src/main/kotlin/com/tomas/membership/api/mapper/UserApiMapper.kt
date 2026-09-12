package com.tomas.membership.api.mapper

import com.tomas.membership.api.response.UserResponse
import com.tomas.membership.domain.model.User

object UserApiMapper {

    fun toResponse(user: User): UserResponse {
        return UserResponse(
            id = requireNotNull(user.id),
            email = user.email,
            firstName = user.firstName,
            lastName = user.lastName,
            createdAt = requireNotNull(user.createdAt)
        )
    }
}