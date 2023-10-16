package com.example.studentapplication.domin.use_cases.auth

data class AuthUseCases(
    val registerUseCase: RegisterUseCase,
    val loginUseCase: LoginUseCase ,
    val resetPasswordUseCase: ResetPasswordUseCase
)
