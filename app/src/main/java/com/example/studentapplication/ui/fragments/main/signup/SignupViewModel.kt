package com.example.studentapplication.ui.fragments.main.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentapplication.data.remote.response.StudentDto
import com.example.studentapplication.domin.model.Student
import com.example.studentapplication.domin.repository.AuthRepository
import com.example.studentapplication.utils.State
import com.example.studentapplication.utils.genericFunctions.validateClassName
import com.example.studentapplication.utils.genericFunctions.validateEmail
import com.example.studentapplication.utils.genericFunctions.validateName
import com.example.studentapplication.utils.genericFunctions.validatePassword
import com.example.studentapplication.utils.genericFunctions.validatePhone
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _registerLiveData = MutableLiveData<State<StudentDto?>>()
    val registerLiveData: LiveData<State<StudentDto?>> = _registerLiveData

    private val _registerFields = Channel<RegisterFieldsState>()
    val registerFields = _registerFields.receiveAsFlow()


    fun register(student: Student) {
        if (checkRegisterValidation(student)) {
            viewModelScope.launch {
                _registerLiveData.value = authRepository.register(student)
            }
        } else {
            val registerFieldsState = RegisterFieldsState(
                email = validateEmail(student.email),
                password = validatePassword(student.password),
                name = validateName(student.fullName),
                phone = validatePhone(student.phone),
                className = validateClassName(student.className)
            )
            viewModelScope.launch {
                _registerFields.send(registerFieldsState)
            }
        }
    }

}




















