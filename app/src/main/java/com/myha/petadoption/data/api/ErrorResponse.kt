package com.myha.petadoption.data.api

data class ErrorResponse(
    val code: Int,
    val message: String,
    val data: Any
)