package com.example.studentapplication.data.repository

import com.example.studentapplication.data.remote.ApiService
import com.example.studentapplication.data.remote.response.auth.LoginResponse
import com.example.studentapplication.data.remote.response.auth.RegisterResponse
import com.example.studentapplication.domin.repository.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : AuthRepository {
    override suspend fun register(
        email: String,
        password: String,
        name: String,
        phone: String,
        address: String,
        srialNumber: String
    ): Response<RegisterResponse?> =
        apiService.register(email, password, name, phone, address, srialNumber)


    override suspend fun login(email: String, password: String): Response<LoginResponse?> =
        apiService.login(email, password)




}