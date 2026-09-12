package com.tomas.membership.api.exception

import com.tomas.membership.api.response.ApiResponse
import com.tomas.membership.domain.exception.EmailAlreadyExistsException
import com.tomas.membership.domain.exception.UserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFound(
        exception: UserNotFoundException
    ): ResponseEntity<ApiResponse<Nothing>> {

        val response = ApiResponse(
            status = HttpStatus.NOT_FOUND.value(),
            detail = exception.message ?: "User not found",
            data = null
        )

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(response)
    }

    @ExceptionHandler(EmailAlreadyExistsException::class)
    fun handleEmailAlreadyExists(
        exception: EmailAlreadyExistsException
    ): ResponseEntity<ApiResponse<Nothing>> {

        val response = ApiResponse<Nothing>(
            status = HttpStatus.CONFLICT.value(),
            detail = exception.message ?: "Email already exists",
            data = null
        )

        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(response)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(
        exception: MethodArgumentNotValidException
    ): ResponseEntity<ApiResponse<Nothing>> {

        val detail = exception.bindingResult
            .fieldErrors
            .joinToString(", ") { error ->
                "${error.field}: ${error.defaultMessage}"
            }

        val response = ApiResponse<Nothing>(
            status = HttpStatus.BAD_REQUEST.value(),
            detail = detail,
            data = null
        )

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(response)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(
        exception: Exception
    ): ResponseEntity<ApiResponse<Nothing>> {

        val response = ApiResponse<Nothing>(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            detail = "Internal server error",
            data = null
        )

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(response)
    }
}