package com.example.studentapplication.data.remote

import com.example.studentapplication.data.remote.response.ResetPasswordResponse
import com.example.studentapplication.data.remote.response.StudentDto
import com.example.studentapplication.domin.model.Student
import com.example.studentapplication.domin.model.StudentProfile
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {

    @POST("api/signup")
    suspend fun register(
        @Body student: Student
    ): Response<StudentDto?>

    @POST("api/login")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<StudentDto?>

    @POST("api/reset-password")
    suspend fun resetPassword(
        @Query("new_password") password: String,
        @Header("Authorization") token: String
    ): Response<ResetPasswordResponse>

    @Multipart
    @POST("api/update-profile")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Part("name")  name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("new_password") newPassword: RequestBody?,
        @Part image: MultipartBody.Part
    ): Response<StudentDto>
}











