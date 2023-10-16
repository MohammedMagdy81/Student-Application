package com.example.studentapplication.domin.use_cases.auth

import com.example.studentapplication.domin.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String) =
        authRepository.login(email, password)
}