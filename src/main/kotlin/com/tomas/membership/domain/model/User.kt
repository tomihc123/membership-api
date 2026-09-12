    package com.tomas.membership.domain.model

    import java.time.LocalDateTime

    data class User(
        val id: Long? = null,
        val email: String,
        val firstName: String,
        val lastName: String,
        val createdAt: LocalDateTime? = null
    )