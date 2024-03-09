package com.jpmc.planetapp.core.common

sealed class Result<T>(val data: T? = null, val errorMsg: String? = "") {
    class Success<T>(data: T) : Result<T>(data = data)
    class Failure<T>(errorMsg: String?) : Result<T>(errorMsg = errorMsg)
    class Loading<T> : Result<T>()
}