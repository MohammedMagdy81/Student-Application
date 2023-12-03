package com.example.studentapplication.data.remote.response.get_lectures

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccessEmail(
    val codeAccess: String,
    val emailAccsess: String,
    val id: Int,
    val lectureId: Int
) : Parcelable