package com.example.studentapplication.domin.use_cases.auth

import com.example.studentapplication.domin.model.Student
import com.example.studentapplication.domin.repository.AuthRepository

class RegisterUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(student: Student) = authRepository.register(student)


}