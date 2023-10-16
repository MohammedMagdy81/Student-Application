package com.example.studentapplication.ui.fragments.main.login

import androidx.lifecycle.*
import com.example.studentapplication.data.remote.response.ResetPasswordResponse
import com.example.studentapplication.data.remote.response.StudentDto
import com.example.studentapplication.domin.use_cases.auth.AuthUseCases
import com.example.studentapplication.utils.State
import com.example.studentapplication.utils.genericFunctions.validateEmail
import com.example.studentapplication.utils.genericFunctions.validatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {

    private val _loginResult = MutableStateFlow<State<StudentDto?>>(State.Ideal)
    val loginResult: StateFlow<State<StudentDto?>> = _loginResult

    private val errorHandler = CoroutineExceptionHandler { _, t ->
        viewModelScope.launch {
            _loginResult.emit(State.Error(t.message.toString()))
        }
    }

    private val _resetPassword = MutableStateFlow<State<ResetPasswordResponse>>(State.Ideal)
    val resetPassword: StateFlow<State<ResetPasswordResponse>> = _resetPassword

    private val _loginValidation = Channel<LoginFieldsState>()
    val loginValidation = _loginValidation.receiveAsFlow()


    fun login(email: String, password: String) {
        if (checkLoginValidation(email, password)) {
            runBlocking {
                _loginResult.emit(State.Loading)
            }
            viewModelScope.launch(errorHandler) {
                val response = authUseCases.loginUseCase(email = email, password = password)
                if (response.isSuccessful)
                    _loginResult.emit(State.Success(response.body()))
                else
                    _loginResult.emit(State.Error(response.message()))
            }

        } else {
            val loginFieldsState = LoginFieldsState(
                email = validateEmail(email),
                password = validatePassword(password)
            )
            viewModelScope.launch {
                _loginValidation.send(loginFieldsState)
            }
        }
    }

    fun resetPassword(token: String, newPassword: String) {
        runBlocking {
            _resetPassword.emit(State.Loading)
        }
        viewModelScope.launch {
            val response =
                authUseCases.resetPasswordUseCase(token = token, newPassword = newPassword)
            if (response.isSuccessful)
                _resetPassword.emit(State.Success(response.body()))
            else
                _resetPassword.emit(State.Error(response.message()))
        }

    }

}



















