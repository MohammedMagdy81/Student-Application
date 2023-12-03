package com.example.studentapplication.ui.fragments.main.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.studentapplication.data.remote.response.ForgetPasswordResponse
import com.example.studentapplication.data.remote.response.StudentDto
import com.example.studentapplication.domin.model.ForgetPasswordRequest
import com.example.studentapplication.domin.repository.AuthRepository
import com.example.studentapplication.utils.Network
import com.example.studentapplication.utils.PreferencesKeys
import com.example.studentapplication.utils.State
import com.example.studentapplication.utils.genericFunctions.validateEmail
import com.example.studentapplication.utils.genericFunctions.validatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val app: Application
) : AndroidViewModel(app) {

    private val _loginResult = MutableLiveData<State<StudentDto?>>()
    val loginResult: LiveData<State<StudentDto?>> = _loginResult

    private val _resetPassword = MutableLiveData<State<ForgetPasswordResponse?>>()
    val resetPassword: LiveData<State<ForgetPasswordResponse?>> = _resetPassword

    private val _loginValidation = Channel<LoginFieldsState>()
    val loginValidation = _loginValidation.receiveAsFlow()

    private val _passwordValidation = Channel<PasswordFieldState>()
    val passwordValidation = _passwordValidation.receiveAsFlow()


    fun login(email: String, password: String) {
        if (checkLoginValidation(email, password)) {
            if (Network.isConnected()) {
                viewModelScope.launch {
                    _loginResult.value = authRepository.login(email, password)
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


}



















