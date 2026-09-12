package com.tomas.membership.application.usecase

import com.tomas.membership.application.repository.UserRepository
import com.tomas.membership.domain.exception.UserNotFoundException
import com.tomas.membership.domain.model.User
import org.springframework.stereotype.Service

@Service
class GetUserUseCase(
    private val userRepository: UserRepository
) {

    fun execute(id: Long): User {
        return userRepository.findById(id)
            ?: throw UserNotFoundException(id)
    }
}