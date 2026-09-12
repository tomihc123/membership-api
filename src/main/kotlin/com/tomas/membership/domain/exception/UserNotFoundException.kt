package com.tomas.membership.domain.exception

class UserNotFoundException(
    id: Long
) : RuntimeException(
    "User with id '$id' was not found"
)