package com.example.mynewsapplication.util

import retrofit2.Response

inline fun <T, R> Response<T>.call(function: ((T) -> R)): R = if (this.isSuccessful) {
    val response = this.body() ?: throw AppExceptions.TechnicalError()
    try {
        function.invoke(response)
    } catch (e: Exception) {
        throw e
//        throw AppExceptions.TechnicalError
    }
} else {
    throw when (this.code()) {
        403 ->  AppExceptions.ServiceNotAvailable
        500 -> AppExceptions.ServerError
        else -> AppExceptions.TechnicalError()
    }
}