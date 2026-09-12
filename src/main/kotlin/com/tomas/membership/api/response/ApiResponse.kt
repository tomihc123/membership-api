package com.tomas.membership.api.response

data class ApiResponse<T>(
    val status: Int,
    val detail: String,
    val data: T? = null
)