package com.example.studentapplication.ui.fragments.main.login

import com.example.studentapplication.utils.AuthValidations
import com.example.studentapplication.utils.genericFunctions.validateEmail
import com.example.studentapplication.utils.genericFunctions.validatePassword

data class LoginFieldsState(
    val email: AuthValidations,
    val password: AuthValidations
)


fun checkLoginValidation(email: String, password: String): Boolean {
    val validateEmail = validateEmail(email)
    val validatePassword = validatePassword(password)

    return validateEmail is AuthValidations.Success &&
            validatePassword is AuthValidations.Success
}