package com.example.mynewsapplication.util

sealed class AppExceptions: Exception() {
    data object ServiceNotAvailable : AppExceptions()
    data object ServerError : AppExceptions()
    data class TechnicalError(val errorMessage: String? = null) : AppExceptions()
}