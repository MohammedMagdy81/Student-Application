package com.example.studentapplication.domin.repository

import com.example.studentapplication.data.remote.response.ResetPasswordResponse
import com.example.studentapplication.data.remote.response.StudentDto
import com.example.studentapplication.domin.model.Student
import retrofit2.Response

interface AuthRepository {

    suspend fun register(student: Student): Response<StudentDto?>

    suspend fun login(email: String, password: String): Response<StudentDto?>

    suspend fun resetPassword(token:String,newPassword:String):Response<ResetPasswordResponse>

}