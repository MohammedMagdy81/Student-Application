package com.example.studentapplication.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseProfile(
    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("phone")
    val phone: Int? = null,

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("image_url")
    val imageUrl: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("message")
    val message: String? = null,


)
