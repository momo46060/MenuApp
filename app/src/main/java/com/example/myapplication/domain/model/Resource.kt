package com.example.myapplication.domain.model


sealed class Resource<out T> {
    object Idle : Resource<Nothing>()
    object Loading : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val message: String?, val throwable: Throwable? = null) : Resource<Nothing>()
}