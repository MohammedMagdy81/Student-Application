package com.example.studentapplication.ui.fragments.main.signup

import com.example.studentapplication.domin.model.Student
import com.example.studentapplication.utils.AuthValidations
import com.example.studentapplication.utils.genericFunctions.*

data class RegisterFieldsState(
    val email: AuthValidations,
    val password: AuthValidations,
    val name: AuthValidations,
    val phone: AuthValidations,
    val className:AuthValidations
)

fun checkRegisterValidation(student: Student): Boolean {
    val validateEmail = validateEmail(student.email)
    val validatePassword = validatePassword(student.password)
    val validateName = validateName(student.fullName)
    val validatePhone = validatePhone(student.phone)
    val validateClassName = validateClassName(student.className)

    return validateEmail is AuthValidations.Success &&
            validatePassword is AuthValidations.Success &&
            validateName is AuthValidations.Success &&
            validatePhone is AuthValidations.Success &&
            validateClassName is AuthValidations.Success
}
