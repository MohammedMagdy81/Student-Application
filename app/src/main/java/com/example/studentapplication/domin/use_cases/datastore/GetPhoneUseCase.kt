package com.example.studentapplication.domin.use_cases.datastore

import com.example.studentapplication.domin.repository.StudentRepository

class GetPhoneUseCase(
    private val studentRepository: StudentRepository
) {

     operator fun invoke() =studentRepository.getPhone()
}