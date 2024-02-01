package com.example.studentapplication.domin.repository

import com.example.studentapplication.data.remote.response.quizzes.AddStudentQuizResponse
import com.example.studentapplication.data.remote.response.quizzes.GetAllQuizResponse
import com.example.studentapplication.domin.model.StudentQuizBody
import retrofit2.Response

interface QuizRepository {

    suspend fun getAllQuiz(): Response<List<GetAllQuizResponse>>

    suspend fun addStudentQuiz(studentQuizBody: StudentQuizBody): Response<AddStudentQuizResponse>
}