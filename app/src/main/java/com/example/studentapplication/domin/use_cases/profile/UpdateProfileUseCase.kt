package com.example.studentapplication.domin.use_cases.profile

import com.example.studentapplication.domin.model.StudentProfile
import com.example.studentapplication.domin.repository.ProfileRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class UpdateProfileUseCase(
    private val profileRepository: ProfileRepository
) {

    suspend operator fun invoke(
        token: String,
        name: RequestBody,
        email: RequestBody,
        phone: RequestBody,
        newPassword: RequestBody?,
        image: MultipartBody.Part
    ) =
        profileRepository.updateProfile(token, name, email, phone, newPassword, image)

}