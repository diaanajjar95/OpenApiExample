package com.example.openapiexample.data.source.remote

import retrofit2.Response

sealed class DefaultResponse<T> {

    data class Fail<T>(val error: Throwable) : DefaultResponse<T>()
    data class Success<T>(val body: T) : DefaultResponse<T>()

    companion object {

        fun <T> create(response: Response<T>): DefaultResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                Success(body ?: throw EmptyResponseException())
            } else
                Fail(Exception())
        }

        fun <T> create(error: Throwable): Fail<T> {
            return Fail(error)
        }

    }

}