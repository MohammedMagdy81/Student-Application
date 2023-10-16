package com.example.studentapplication.domin.use_cases.datastore

import com.example.studentapplication.domin.repository.StudentRepository

class SetEmailUseCase(
    private val studentRepository: StudentRepository
) {
    suspend operator fun invoke(email: String) = studentRepository.setEmail(email)
}