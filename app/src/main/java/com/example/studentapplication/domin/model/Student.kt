package com.example.studentapplication.domin.model

import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("email")
    val email:String,
    @SerializedName("password")
    val password:String ,
    @SerializedName("phone")
    val phone:String,
    @SerializedName("name")
    val fullName:String,
    @SerializedName("class_name")
    val className:String
)
