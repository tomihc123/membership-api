package com.tomas.membership.domain.exception

class EmailAlreadyExistsException(
    email: String
) : RuntimeException(
    "A user with email '$email' already exists"
)