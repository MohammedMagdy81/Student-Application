package com.example.studentapplication.domin.repository

import com.example.studentapplication.data.remote.response.quizzes.GetAllQuizResponse
import retrofit2.Response

interface QuizRepository {

    suspend fun getAllQuiz():Response<List<GetAllQuizResponse>>
}