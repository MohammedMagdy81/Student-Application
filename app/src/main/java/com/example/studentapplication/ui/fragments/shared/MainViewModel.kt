package com.example.studentapplication.ui.fragments.shared

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentapplication.data.local.StudentDatastore
import com.example.studentapplication.domin.use_cases.datastore.DatastoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreUseCase:DatastoreUseCases
): ViewModel() {

    fun setId(id: Int) {
        viewModelScope.launch {
            dataStoreUseCase.setIDUseCase(id)
        }
    }
    fun setToken(token: String) {
        viewModelScope.launch {
            dataStoreUseCase.setTokenUseCase(token)
        }
    }

    fun setPhone(phone: String) {
        viewModelScope.launch {
            dataStoreUseCase.setPhoneUseCase(phone)
        }
    }

    fun setName(name: String) {
        viewModelScope.launch {
            dataStoreUseCase.setNameUseCase(name)
        }
    }

    fun setEmail(email: String) {
        viewModelScope.launch {
            dataStoreUseCase.setEmailUseCase(email)
        }
    }

    fun getId() = dataStoreUseCase.getIDUseCase()
    fun getToken() = dataStoreUseCase.getTokenUseCase()
    fun getName() = dataStoreUseCase.getNameUseCase()
    fun getPhone() = dataStoreUseCase.getPhoneUseCase()
    fun getEmail() = dataStoreUseCase.getEmailUseCase()

}