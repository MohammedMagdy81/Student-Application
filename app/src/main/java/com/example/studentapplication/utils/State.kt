package com.example.studentapplication.utils

sealed class State<out T>(val data: T?) {

    class Success<T>(data: T?) : State<T>(data)

    data class Error(val message: String) : State<Nothing>(null)

    object Loading : State<Nothing>(null)

    object Ideal : State<Nothing>(null)
}
