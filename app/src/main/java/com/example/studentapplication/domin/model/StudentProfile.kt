package com.example.studentapplication.domin.model

import com.google.gson.annotations.SerializedName

data class StudentProfile(
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("new_password") val new_Password: String,
    @SerializedName("phone") val imageUrl: String? = null
)
