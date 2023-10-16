package com.example.studentapplication.data.repository

import com.example.studentapplication.data.remote.ApiService
import com.example.studentapplication.data.remote.response.ResetPasswordResponse
import com.example.studentapplication.data.remote.response.StudentDto
import com.example.studentapplication.domin.model.Student
import com.example.studentapplication.domin.repository.AuthRepository
import retrofit2.Response

class AuthRepositoryImpl(private val apiService: ApiService) : AuthRepository {

    override suspend fun register(student: Student): Response<StudentDto?> =
        apiService.register(student)

    override suspend fun login(email: String, password: String): Response<StudentDto?> =
        apiService.login(email, password)

    override suspend fun resetPassword(
        token: String,
        newPassword: String
    ): Response<ResetPasswordResponse> =
        apiService.resetPassword(token = "Bearer $token", password = newPassword)

}