package com.example.studentapplication.domin.use_cases.datastore

import com.example.studentapplication.domin.repository.StudentRepository

class SetPhoneUseCase(
    private val studentRepository: StudentRepository
) {
    suspend operator fun invoke(phone: String) = studentRepository.setPhone(phone)
}