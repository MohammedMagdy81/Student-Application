package com.example.studentapplication.ui.fragments.settings.edit_profile

import com.example.studentapplication.utils.AuthValidations
import com.example.studentapplication.utils.genericFunctions.validateEmail
import com.example.studentapplication.utils.genericFunctions.validatePassword
import okhttp3.RequestBody

fun checkUpdateProfile(_name: RequestBody,
                       _email: RequestBody,
                       _phone: RequestBody,
                       _newPassword: RequestBody?): Boolean {
    val email = validateEmail(_email.toString())
    val phone = validateEmail(_phone.toString())
    val name = validateEmail(_name.toString())
    val password = validatePassword(_newPassword.toString())

    return email is AuthValidations.Success &&
            phone is AuthValidations.Success &&
            name is AuthValidations.Success &&
            password is AuthValidations.Success
}

data class UpdateProfileFieldsState(
    val email: AuthValidations,
    val phone: AuthValidations,
    val name: AuthValidations,
    val password: AuthValidations,
)