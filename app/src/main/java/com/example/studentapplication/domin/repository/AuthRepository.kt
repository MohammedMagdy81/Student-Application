package com.example.studentapplication.domin.repository

import com.example.studentapplication.data.remote.response.auth.LoginResponse
import com.example.studentapplication.data.remote.response.auth.RegisterResponse
import retrofit2.Response

interface AuthRepository {

    suspend fun register(
        email: String,
        password: String,
        name: String,
        phone: String,
        address: String,
        srialNumber: String
    ): Response<RegisterResponse?>

    suspend fun login(email: String, password: String): Response<LoginResponse?>



}