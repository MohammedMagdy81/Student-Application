package com.example.studentapplication.domin.use_cases.datastore

import com.example.studentapplication.domin.repository.StudentRepository

class SetIDUseCase(
    private val studentRepository: StudentRepository
) {
    suspend operator fun invoke(id: Int) = studentRepository.setID(id)
}