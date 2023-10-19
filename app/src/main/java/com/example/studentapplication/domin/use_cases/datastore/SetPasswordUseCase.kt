package com.example.studentapplication.domin.use_cases.datastore

import com.example.studentapplication.domin.repository.StudentRepository

class SetPasswordUseCase(
    private val studentRepository: StudentRepository
) {
    suspend operator fun invoke(password: String) = studentRepository.setPassword(password)
}