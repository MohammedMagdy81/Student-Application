package com.example.studentapplication.data.remote

import com.example.studentapplication.data.remote.response.ForgetPasswordResponse
import com.example.studentapplication.data.remote.response.LogoutResponse
import com.example.studentapplication.data.remote.response.ResponseProfile
import com.example.studentapplication.data.remote.response.StudentDto
import com.example.studentapplication.data.remote.response.get_lectures.GetLecturesResponseItem
import com.example.studentapplication.domin.model.ForgetPasswordRequest
import com.example.studentapplication.domin.model.LoginRequest
import com.example.studentapplication.domin.model.Student
import com.example.studentapplication.domin.model.UpdateUserRequest
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("api/auth/signup")
    suspend fun register(
        @Body student: Student
    ): StudentDto?

    @POST("api/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): StudentDto?


    @GET("api/auth/profile")
    suspend fun getUserProfile(
        @Header("Authorization") token: String,
    ): ResponseProfile

    @PUT("api/auth/profile")
    suspend fun updateUser(
        @Header("Authorization") token: String,
        @Body updateUser: UpdateUserRequest,
    ): ResponseProfile

    @GET("api/Lecture/GetAllLecture")
    suspend fun getAllLectures(
        @Header("Authorization") token: String
    ): List<GetLecturesResponseItem>


}











