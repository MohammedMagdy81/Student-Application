package com.example.studentapplication.data.remote.response.get_lectures

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetLecturesResponseItem(
    val accessEmails: List<AccessEmail>,
    val date: String,
    val filePath: String,
    val id: Int,
    val lecture_Desc: String,
    val lecture_Name: String,
    val lecture_Note: String,
    val lecture_Price: Int,
    val lecture_Subject: String,
    val pictureUrl: String,
    val teacherName: String,
    val videoPath: String
) : Parcelable