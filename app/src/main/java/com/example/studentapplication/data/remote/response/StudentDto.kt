package com.example.studentapplication.data.remote.response

import com.google.gson.annotations.SerializedName

data class StudentDto(
    @field:SerializedName("name")
    val fullName: String,
    @field:SerializedName("email")
    val email: String,
    @field:SerializedName("token")
    val token: String,
    @field:SerializedName("class_name")
    val className: String,
    @field:SerializedName("phone")
    val phone: String,
    @field:SerializedName("image_url")
    val imageUrl: String? = null
)
