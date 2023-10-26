package com.example.studentapplication.domin.model

import com.google.gson.annotations.SerializedName

data class Lecture(
    @SerializedName("lecture_image")
    val lectureImage: String,

    @SerializedName("lecture_price")
    val lecturePrice:String,

    @SerializedName("lecture_subject")
    val lectureSubject:String,

    @SerializedName("lecture_name")
    val lectureName:String,

    @SerializedName("teacher_name")
    val teacherName:String,

    )
