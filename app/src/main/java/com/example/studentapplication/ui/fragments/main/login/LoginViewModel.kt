package com.example.studentapplication.ui.fragments.main.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.studentapplication.R
import com.example.studentapplication.data.remote.response.ResetPasswordResponse
import com.example.studentapplication.data.remote.response.StudentDto
import com.example.studentapplication.domin.use_cases.auth.AuthUseCases
import com.example.studentapplication.utils.Network
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
    private val authUseCases: AuthUseCases,
    private val app: Application
) : AndroidViewModel(app) {

    private val _loginResult = MutableStateFlow<State<StudentDto?>>(State.Ideal)
    val loginResult: StateFlow<State<StudentDto?>> = _loginResult

    private val _resetPassword = MutableStateFlow<State<ResetPasswordResponse>>(State.Ideal)
    val resetPassword: StateFlow<State<ResetPasswordResponse>> = _resetPassword

    private val _loginValidation = Channel<LoginFieldsState>()
    val loginValidation = _loginValidation.receiveAsFlow()

    private val _passwordValidation = Channel<PasswordFieldState>()
    val passwordValidation = _passwordValidation.receiveAsFlow()

    private val errorHandler = CoroutineExceptionHandler { _, t ->
        viewModelScope.launch {
            _loginResult.emit(State.Error(t.message.toString()))
        }
    }


    fun login(email: String, password: String) {
        if (checkLoginValidation(email, password)) {
            if (Network.isConnected()) {
                try {
                    viewModelScope.launch(errorHandler) {
                        _loginResult.emit(State.Loading)
                        Log.d("Sates", "login: Loading")
                        val response = authUseCases.loginUseCase(email = email, password = password)
                        if (response.isSuccessful) {
                            _loginResult.emit(State.Success(response.body()))
                            Log.d("Sates", "login: Success")
                        } else {
                            _loginResult.emit(State.Error(response.message()))
                            Log.d("Sates", "login: Error")
                        }
                    }
                } catch (e: Exception) {
                    viewModelScope.launch {
                        _loginResult.emit(State.Error(e.message.toString()))
                        Log.d("Sates", "login: Error")
                    }
                }

            } else {
                viewModelScope.launch {
                    _loginResult.emit(State.Error(app.getString(R.string.no_internet_found)))
                }
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
        if (checkPassword(password = newPassword)) {
            try {
                runBlocking {
                    _resetPassword.emit(State.Loading)
                    Log.d("Sates", "password: Loading")
                }
                viewModelScope.launch(errorHandler) {
                    val response =
                        authUseCases.resetPasswordUseCase(token = token, newPassword = newPassword)
                    if (response.isSuccessful) {
                        _resetPassword.emit(State.Success(response.body()))
                        Log.d("Sates", "password: Success")
                    } else {
                        _resetPassword.emit(State.Error(response.message()))
                        Log.d("Sates", "password: Error")
                    }
                }
            } catch (e: Exception) {
                viewModelScope.launch {
                    _resetPassword.emit(State.Error(e.message.toString()))
                    Log.d("Sates", "password: Error in catch")
                }
            }

        } else {
            val validatePasswordState = PasswordFieldState(
                password = validatePassword(newPassword)
            )
            viewModelScope.launch {
                _passwordValidation.send(validatePasswordState)
            }
        }
    }

}



















