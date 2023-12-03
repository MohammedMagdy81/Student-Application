package com.example.studentapplication.domin.repository

import com.example.studentapplication.data.remote.response.get_lectures.GetLecturesResponseItem
import com.example.studentapplication.utils.State
import retrofit2.Response

interface LecturesRepository {

    suspend fun getAllLectures(token:String): State<List<GetLecturesResponseItem>>
}