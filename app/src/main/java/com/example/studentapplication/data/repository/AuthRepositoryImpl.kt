package com.example.studentapplication.data.repository

import com.example.studentapplication.data.remote.ApiService
import com.example.studentapplication.data.remote.response.ForgetPasswordResponse
import com.example.studentapplication.data.remote.response.LogoutResponse
import com.example.studentapplication.data.remote.response.StudentDto
import com.example.studentapplication.data.remote.safeApiCall
import com.example.studentapplication.domin.model.ForgetPasswordRequest
import com.example.studentapplication.domin.model.LoginRequest
import com.example.studentapplication.domin.model.Student
import com.example.studentapplication.domin.repository.AuthRepository
import com.example.studentapplication.utils.PreferencesKeys.ACCESS_TOKEN
import com.example.studentapplication.utils.PreferencesKeys.CLASS_NAME
import com.example.studentapplication.utils.PreferencesKeys.EMAIL
import com.example.studentapplication.utils.PreferencesKeys.NAME
import com.example.studentapplication.utils.PreferencesKeys.PHONE
import com.example.studentapplication.utils.State
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : AuthRepository {
    override suspend fun register(student: Student): State<StudentDto?> =
        safeApiCall {
            apiService.register(student)
        }


    override suspend fun login(email: String, password: String): State<StudentDto?> =
        safeApiCall {
            apiService.login(LoginRequest(email = email, password = password))
        }




}