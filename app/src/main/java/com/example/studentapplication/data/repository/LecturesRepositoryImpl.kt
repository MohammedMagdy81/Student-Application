package com.example.studentapplication.data.repository

import com.example.studentapplication.data.remote.ApiService
import com.example.studentapplication.data.remote.response.get_lectures.GetLecturesResponseItem
import com.example.studentapplication.data.remote.safeApiCall
import com.example.studentapplication.domin.repository.LecturesRepository
import retrofit2.Response
import javax.inject.Inject

class LecturesRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : LecturesRepository {
    override suspend fun getAllLectures(token: String) =
        apiService.getAllLectures(token = "Bearer $token")

}