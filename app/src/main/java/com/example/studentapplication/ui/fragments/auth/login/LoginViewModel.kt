package com.example.studentapplication.ui.fragments.auth.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.studentapplication.data.remote.response.auth.LoginResponse
import com.example.studentapplication.domin.repository.AuthRepository
import com.example.studentapplication.utils.Network
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

    private val _loginResult = MutableLiveData<State<LoginResponse?>>()
    val loginResult: LiveData<State<LoginResponse?>> = _loginResult


    private val _loginValidation = Channel<LoginFieldsState>()
    val loginValidation = _loginValidation.receiveAsFlow()

    fun login(email: String, password: String) {
        if (Network.isConnected()) {
            if (checkLoginValidation(email, password)) {
                _loginResult.postValue(State.Loading)
                viewModelScope.launch {
                    val response = authRepository.login(email, password)
                    if (response.isSuccessful) {
                        _loginResult.postValue(State.Success(response.body()))
                    } else {
                        when (response.code()) {
                            400 -> {
                                _loginResult.postValue(State.Failure("تأكد من صيغة الايميل وكلمة السر "))
                            }

                            401 -> {
                                _loginResult.postValue(State.Failure("هذا الايميل غير مسجل لدينا ! أو ربما تكون كلمة السر غير صحيحة "))
                            }

                            else -> {
                                _loginResult.postValue(State.Failure("حدث خطأ غير متوقع حاول مرة أخري !"))
                            }
                        }
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
        } else _loginResult.postValue(State.Failure("لا يتوفر إنترت تأكد من اتصالك جيدا ثم أعد المحاولة !"))
    }


}




