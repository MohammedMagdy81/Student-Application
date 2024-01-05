package com.example.studentapplication.utils

sealed class State<out T>(val data: T? = null) {

    class Success<T>(data: T?) : State<T>(data)

    data class Failure(
        val errorMessage: String
    ) : State<Nothing>()

    data class NoInternet(
        val errorMessage: String
    ) : State<Nothing>()

    object Loading : State<Nothing>(null)



}
