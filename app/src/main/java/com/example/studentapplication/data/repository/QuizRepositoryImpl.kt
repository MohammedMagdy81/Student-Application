package com.example.studentapplication.data.repository

import com.example.studentapplication.data.remote.ApiService
import com.example.studentapplication.data.remote.response.quizzes.AddStudentQuizResponse
import com.example.studentapplication.data.remote.response.quizzes.GetAllQuizResponse
import com.example.studentapplication.domin.model.StudentQuizBody
import com.example.studentapplication.domin.repository.QuizRepository
import retrofit2.Response
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : QuizRepository {
    override suspend fun getAllQuiz(): Response<List<GetAllQuizResponse>> =
        apiService.getAllQuiz()

    override suspend fun addStudentQuiz(studentQuizBody: StudentQuizBody): Response<AddStudentQuizResponse> =
        apiService.addStudentQuiz(studentQuizBody)
}