package com.tomas.membership.application.usecase

import com.tomas.membership.application.repository.UserRepository
import com.tomas.membership.domain.model.User
import org.springframework.stereotype.Service

@Service
class ListUsersUseCase(
    private val userRepository: UserRepository
) {

    fun execute(): List<User> {
        return userRepository.findAll()
    }
}