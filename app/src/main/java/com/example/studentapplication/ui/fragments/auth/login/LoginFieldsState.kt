package com.example.studentapplication.ui.fragments.auth.login

import com.example.studentapplication.utils.AuthValidations
import com.example.studentapplication.utils.genericFunctions.validateEmail
import com.example.studentapplication.utils.genericFunctions.validatePassword

data class LoginFieldsState(
    val email: AuthValidations,
    val password: AuthValidations
)

data class PasswordFieldState(
    val email: AuthValidations
)
fun checkLoginValidation(email: String, password: String): Boolean {
    val validateEmail = validateEmail(email)
    val validatePassword = validatePassword(password)

    return validateEmail is AuthValidations.Success &&
            validatePassword is AuthValidations.Success
}

fun checkEmailResetPassword(email: String): Boolean {
    val validateEmail = validateEmail(email)
    return validateEmail is AuthValidations.Success
}

