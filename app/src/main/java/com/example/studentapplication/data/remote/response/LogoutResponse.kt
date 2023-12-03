package com.example.studentapplication.data.remote.response

import com.google.gson.annotations.SerializedName

data class LogoutResponse(
    @field:SerializedName("code")
    val code: Int? = null,
    @field:SerializedName("success")
    val success: Boolean? = null,
    @field:SerializedName("message")
    val message: String? = null,
)
