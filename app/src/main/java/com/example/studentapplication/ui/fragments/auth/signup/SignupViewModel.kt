package com.example.studentapplication.ui.fragments.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentapplication.data.remote.response.auth.RegisterResponse
import com.example.studentapplication.domin.repository.AuthRepository
import com.example.studentapplication.utils.Network
import com.example.studentapplication.utils.State
import com.example.studentapplication.utils.genericFunctions.validateAddress
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

    private val _registerLiveData = MutableLiveData<State<RegisterResponse?>>()
    val registerLiveData: LiveData<State<RegisterResponse?>> = _registerLiveData

    private val _registerFields = Channel<RegisterFieldsState>()
    val registerFields = _registerFields.receiveAsFlow()


    fun register(
        email: String,
        password: String,
        name: String,
        phone: String,
        address: String,
        srialNumber: String
    ) {
        if (Network.isConnected()) {
            if (checkRegisterValidation(email, password, name, phone, address)) {
                _registerLiveData.postValue(State.Loading)
                viewModelScope.launch {
                    _registerLiveData.postValue(
                        State.Success(
                            authRepository.register(
                                email,
                                password,
                                name,
                                phone,
                                address,
                                srialNumber
                            )
                                .body()
                        )
                    )

                }
            } else {
                val registerFieldsState = RegisterFieldsState(
                    email = validateEmail(email),
                    password = validatePassword(password),
                    name = validateName(name),
                    phone = validatePhone(phone),
                    address = validateAddress(address)
                )
                viewModelScope.launch {
                    _registerFields.send(registerFieldsState)
                }
            }
        } else {
            _registerLiveData.postValue(State.Failure("لا يتوفر إنترنت تأكد من اتصالك جدا بالانترنت !"))
        }

    }

}




















