package com.example.studentapplication.utils

import okhttp3.ResponseBody

sealed class State<out T>(val data: T?= null) {

    class Success<T>(data: T?) : State<T>(data)

    data class Failure(
        val errorMessage:String
    ) : State<Nothing>()

    object Loading : State<Nothing>(null)

    object Ideal : State<Nothing>(null)
}
