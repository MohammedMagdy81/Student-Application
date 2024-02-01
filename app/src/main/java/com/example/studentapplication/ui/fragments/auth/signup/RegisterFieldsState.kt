package com.example.studentapplication.ui.fragments.auth.signup

import com.example.studentapplication.utils.AuthValidations
import com.example.studentapplication.utils.genericFunctions.*

data class RegisterFieldsState(
    val email: AuthValidations,
    val password: AuthValidations,
    val name: AuthValidations,
    val phone: AuthValidations,
    val address:AuthValidations
)

fun checkRegisterValidation(email: String,
                            password: String,
                            name: String,
                            phone: String,
                            address: String): Boolean {
    val validateEmail = validateEmail(email)
    val validatePassword = validatePassword(password)
    val validateName = validateName(name)
    val validatePhone = validatePhone(phone)
    val validateAddress = validateAddress(address)

    return validateEmail is AuthValidations.Success &&
            validatePassword is AuthValidations.Success &&
            validateName is AuthValidations.Success &&
            validatePhone is AuthValidations.Success &&
            validateAddress is AuthValidations.Success
}
