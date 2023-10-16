package com.example.studentapplication.utils

sealed class AuthValidations{
    object Success : AuthValidations()
    data class Error(val message: String) : AuthValidations()
}
