package ru.alexandrorlov.moviescompose.network

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: Any?) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}