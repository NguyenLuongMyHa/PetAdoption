package com.myha.petadoption.data.api

/**
 * Created by Aria on 9/17/2021.
 */
sealed class ResultWrapper {
    data class Success<T>(val value: T) : ResultWrapper()
    data class GenericError(val code: Int? = null, val error: ErrorResponse? = null) :
        ResultWrapper()

    object NetworkError : ResultWrapper()
    data class Loading(val isLoading: Boolean = true) : ResultWrapper()
}