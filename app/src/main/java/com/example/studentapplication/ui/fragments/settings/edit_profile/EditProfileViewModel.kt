package com.example.studentapplication.ui.fragments.settings.edit_profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val app: Application
) : AndroidViewModel(app) {

}