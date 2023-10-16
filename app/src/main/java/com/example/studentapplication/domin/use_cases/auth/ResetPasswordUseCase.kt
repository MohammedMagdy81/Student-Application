package com.example.studentapplication.domin.use_cases.auth

import com.example.studentapplication.domin.repository.AuthRepository

class ResetPasswordUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        token: String,
        newPassword: String
    ) = authRepository.resetPassword(token = token, newPassword = newPassword)
}