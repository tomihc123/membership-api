package com.tomas.membership.application.usecase

import com.tomas.membership.application.repository.UserRepository
import com.tomas.membership.domain.exception.EmailAlreadyExistsException
import com.tomas.membership.domain.model.User
import org.springframework.stereotype.Service

@Service
class CreateUserUseCase(
    private val userRepository: UserRepository
) {

    fun execute(
        email: String,
        firstName: String,
        lastName: String
    ): User {

        if (userRepository.existsByEmail(email)) {
            throw EmailAlreadyExistsException(email)
        }

        val user = User(
            email = email,
            firstName = firstName,
            lastName = lastName
        )

        return userRepository.save(user)
    }
}