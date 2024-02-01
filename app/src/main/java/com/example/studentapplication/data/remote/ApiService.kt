package com.example.studentapplication.data.remote

import com.example.studentapplication.data.remote.response.auth.LoginResponse
import com.example.studentapplication.data.remote.response.auth.RegisterResponse
import com.example.studentapplication.data.remote.response.get_lectures.GetLecturesResponseItem
import com.example.studentapplication.data.remote.response.quizzes.AddStudentQuizResponse
import com.example.studentapplication.data.remote.response.quizzes.GetAllQuizResponse
import com.example.studentapplication.domin.model.StudentQuizBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("api/Student/Register")
    suspend fun register(
        @Query("Email") email: String,
        @Query("Password") password: String,
        @Query("Name") name: String,
        @Query("Phone") phone: String,
        @Query("Address") address: String,
        @Query("SrialNumber") srialNumber: String
    ): Response<RegisterResponse?>

    @POST("api/Student/Login")
    suspend fun login(
        @Query("Email") email: String,
        @Query("Password") password: String,
    ): Response<LoginResponse?>

    @GET("api/Lecture/GetAllLecture")
    suspend fun getAllLectures(
        @Header("Authorization") token: String
    ): Response<List<GetLecturesResponseItem>>

    @GET("api/Quiz/GetAll")
    suspend fun getAllQuiz(
    ): Response<List<GetAllQuizResponse>>


    @POST("api/Quiz/AddStudentsQuiz")
    suspend fun addStudentQuiz(
        @Body studentQuiz: StudentQuizBody
    ): Response<AddStudentQuizResponse>


}











