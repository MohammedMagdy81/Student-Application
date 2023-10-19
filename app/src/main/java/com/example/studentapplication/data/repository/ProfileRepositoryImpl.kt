package com.example.studentapplication.data.repository

import com.example.studentapplication.data.remote.ApiService
import com.example.studentapplication.data.remote.response.StudentDto
import com.example.studentapplication.domin.model.StudentProfile
import com.example.studentapplication.domin.repository.ProfileRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class ProfileRepositoryImpl(
    private val apiService: ApiService
) : ProfileRepository {
    override suspend fun updateProfile(
        token: String,
        name: RequestBody,
        email: RequestBody,
        phone: RequestBody,
        newPassword: RequestBody?,
        image: MultipartBody.Part
    ): Response<StudentDto> =
        apiService.updateProfile(token = "Bearer $token", name, email, phone, newPassword, image)


}