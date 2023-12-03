package com.example.studentapplication.domin.repository

import com.example.studentapplication.data.remote.response.ForgetPasswordResponse
import com.example.studentapplication.data.remote.response.LogoutResponse
import com.example.studentapplication.data.remote.response.StudentDto
import com.example.studentapplication.domin.model.ForgetPasswordRequest
import com.example.studentapplication.domin.model.Student
import com.example.studentapplication.utils.State

interface AuthRepository {

    suspend fun register(student: Student): State<StudentDto?>

    suspend fun login(email: String, password: String): State<StudentDto?>






}