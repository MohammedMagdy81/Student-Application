package com.example.studentapplication.domin.repository

import com.example.studentapplication.data.remote.response.StudentDto
import com.example.studentapplication.domin.model.StudentProfile
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface ProfileRepository {

    suspend fun updateProfile(
        token: String,
        name: RequestBody,
        email: RequestBody,
        phone: RequestBody,
        newPassword: RequestBody?,
        image: MultipartBody.Part
    ): Response<StudentDto>
}