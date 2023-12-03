package com.example.studentapplication.domin.model

import com.google.gson.annotations.SerializedName

data class UpdateUserRequest(
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("email")
    var email: String? = "",
    @SerializedName("phone")
    var phone: String?,
    @SerializedName("address")
    var change_password: Boolean?,
    @SerializedName("current_password")
    var current_password: String?,
    @SerializedName("password")
    var password: String?,
    @SerializedName("password_confirmation")
    var password_confirmation: String?,
)
