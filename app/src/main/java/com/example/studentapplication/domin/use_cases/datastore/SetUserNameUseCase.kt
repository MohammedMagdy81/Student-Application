package com.example.studentapplication.domin.use_cases.datastore

import com.example.studentapplication.domin.repository.StudentRepository

class SetUserNameUseCase(
    private val studentRepository: StudentRepository
) {
    suspend operator fun invoke(userName: String) = studentRepository.setName(userName)
}