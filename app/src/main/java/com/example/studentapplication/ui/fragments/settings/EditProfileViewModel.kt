package com.example.studentapplication.ui.fragments.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentapplication.R
import com.example.studentapplication.data.remote.response.StudentDto
import com.example.studentapplication.domin.model.StudentProfile
import com.example.studentapplication.domin.use_cases.profile.UpdateProfileUseCase
import com.example.studentapplication.utils.Network
import com.example.studentapplication.utils.State
import com.example.studentapplication.utils.genericFunctions.validateEmail
import com.example.studentapplication.utils.genericFunctions.validateName
import com.example.studentapplication.utils.genericFunctions.validatePassword
import com.example.studentapplication.utils.genericFunctions.validatePhone
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val profileUseCase: UpdateProfileUseCase,
    private val app: Application
) : AndroidViewModel(app) {

    private val _updateProfile = MutableStateFlow<State<StudentDto>>(State.Ideal)
    val updateProfile: StateFlow<State<StudentDto>> = _updateProfile

    private val _profileValidations = Channel<UpdateProfileFieldsState>()
    val profileValidations = _profileValidations.receiveAsFlow()


    fun updateProfile(
        token: String,
        name: RequestBody,
        email: RequestBody,
        phone: RequestBody,
        newPassword: RequestBody?,
        image: MultipartBody.Part
    ) {
        if (checkUpdateProfile(name, email, phone, newPassword)) {
            if (Network.isConnected()) {
                viewModelScope.launch {
                    _updateProfile.emit(State.Loading)
                    val response =
                        profileUseCase.invoke(token, name, email, phone, newPassword, image)
                    if (response.isSuccessful) {
                        _updateProfile.emit(State.Success(response.body()))
                    } else {
                        when (response.code()) {
                            500 -> {
                                _updateProfile.emit(State.Error(app.getString(R.string.server_error)))
                            }
                            403 -> {
                                _updateProfile.emit(State.Error(app.getString(R.string.student_is_blocked)))
                            }

                        }
                    }
                }
            } else {
                viewModelScope.launch {
                    _updateProfile.emit(State.Error(app.getString(R.string.internet_unavilable)))
                }
            }

        } else {
            val updateProfileState = UpdateProfileFieldsState(
                email = validateEmail(email.toString()),
                password = validatePassword(newPassword.toString()),
                phone = validatePhone(phone.toString()),
                name = validateName(name.toString())
            )
            viewModelScope.launch {
                _profileValidations.send(updateProfileState)
            }

        }
    }
}