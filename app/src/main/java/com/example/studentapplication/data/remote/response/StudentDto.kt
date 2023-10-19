package com.example.studentapplication.data.remote.response

data class StudentDto(
    val fullName: String,
    val email: String,
    val token: String,
    val className: String,
    val phone: String,
    val image_url: String? = null
)
