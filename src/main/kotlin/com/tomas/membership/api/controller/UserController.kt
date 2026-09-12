package com.tomas.membership.api.controller

import com.tomas.membership.api.mapper.UserApiMapper
import com.tomas.membership.api.request.CreateUserRequest
import com.tomas.membership.api.response.ApiResponse
import com.tomas.membership.api.response.UserResponse
import com.tomas.membership.application.usecase.CreateUserUseCase
import com.tomas.membership.application.usecase.GetUserUseCase
import com.tomas.membership.application.usecase.ListUsersUseCase
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val listUsersUseCase: ListUsersUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(
        @Valid @RequestBody request: CreateUserRequest
    ): ApiResponse<UserResponse> {

        val user = createUserUseCase.execute(
            email = request.email,
            firstName = request.firstName,
            lastName = request.lastName
        )

        return ApiResponse(
            status = HttpStatus.CREATED.value(),
            detail = "User created successfully",
            data = UserApiMapper.toResponse(user)
        )
    }

    @GetMapping("/{id}")
    fun getUser(
        @PathVariable id: Long
    ): ApiResponse<UserResponse> {

        val user = getUserUseCase.execute(id)

        return ApiResponse(
            status = HttpStatus.OK.value(),
            detail = "User retrieved successfully",
            data = UserApiMapper.toResponse(user)
        )
    }

    @GetMapping
    fun getUsers(): ApiResponse<List<UserResponse>> {

        val users = listUsersUseCase.execute()

        return ApiResponse(
            status = HttpStatus.OK.value(),
            detail = "Users retrieved successfully",
            data = users.map { UserApiMapper.toResponse(it) }
        )
    }
}