package com.example.studentapplication.domin.use_cases.datastore

import com.example.studentapplication.domin.repository.StudentRepository

class SetTokenUseCase(
    private val studentRepository: StudentRepository
) {
    suspend operator fun invoke(token: String) = studentRepository.setToken(token)
}