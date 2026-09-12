package com.tomas.membership.infrastructure.repository

import com.tomas.membership.application.repository.UserRepository
import com.tomas.membership.domain.model.User
import com.tomas.membership.infrastructure.persistence.mapper.UserMapper
import com.tomas.membership.infrastructure.persistence.repository.SpringDataUserRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    private val springDataUserRepository: SpringDataUserRepository
) : UserRepository {

    override fun save(user: User): User {
        val entity = UserMapper.toEntity(user)

        val savedEntity = springDataUserRepository.save(entity)

        return UserMapper.toDomain(savedEntity)
    }

    override fun findById(id: Long): User? {
        return springDataUserRepository.findById(id)
            .orElse(null)
            ?.let(UserMapper::toDomain)
    }

    override fun existsByEmail(email: String): Boolean {
        return springDataUserRepository.existsByEmail(email)
    }

    override fun findAll(): List<User> {
        return springDataUserRepository.findAll()
            .map { UserMapper.toDomain(it) }
    }
}