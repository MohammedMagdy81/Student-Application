package com.example.studentapplication.ui.fragments.main.signup

import androidx.lifecycle.*
import com.example.studentapplication.data.remote.response.StudentDto
import com.example.studentapplication.domin.model.Student
import com.example.studentapplication.domin.use_cases.auth.AuthUseCases
import com.example.studentapplication.domin.use_cases.auth.RegisterUseCase
import com.example.studentapplication.utils.State
import com.example.studentapplication.utils.genericFunctions.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {

    private val _registerResult = MutableStateFlow<State<StudentDto?>>(State.Ideal)
    val registerResult: StateFlow<State<StudentDto?>> = _registerResult

    private val _registerFields = Channel<RegisterFieldsState>()
    val registerFields = _registerFields.receiveAsFlow()

    private val errorHandler = CoroutineExceptionHandler { _, t ->
        viewModelScope.launch {
            _registerResult.emit(State.Error(t.message.toString()))
        }
    }


    fun register(student: Student) {
        if (checkRegisterValidation(student)) {
            viewModelScope.launch(errorHandler) {
                val registerResponse = authUseCases.registerUseCase.invoke(student)
                if (registerResponse.isSuccessful)
                    _registerResult.emit(State.Success(registerResponse.body()))
                else
                    _registerResult.emit(State.Error(registerResponse.message()))
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




















