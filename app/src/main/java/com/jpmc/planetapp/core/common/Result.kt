package com.jpmc.planetapp.core.common

sealed class Result<T>(data: T?= null, errorMsg: String? = "") {
    data class Success<T>(val data: T): Result<T>(data= data)
    data class Failure<T>(val errorMsg: String?): Result<T>(errorMsg = errorMsg)
    class Loading<T> : Result<T>()
}