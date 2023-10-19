package com.example.studentapplication.data.repository

import com.example.studentapplication.data.local.StudentDatastore
import com.example.studentapplication.domin.repository.StudentRepository
import kotlinx.coroutines.flow.Flow

class StudentRepositoryImpl(
    private val studentDatastore: StudentDatastore
) : StudentRepository {
    override suspend fun setID(id: Int?) {
        studentDatastore.setTeacherId(id)
    }

    override suspend fun setToken(token: String?) {
        studentDatastore.setToken(token)
    }

    override suspend fun setEmail(email: String?) {
        studentDatastore.setEmail(email)
    }

    override suspend fun setPhone(phone: String?) {
        studentDatastore.setPhoneNo(phone)
    }

    override suspend fun setName(name: String?) {
        studentDatastore.setUserName(name)
    }

    override suspend fun setPassword(password: String?) {
        studentDatastore.setPassword(password)
    }

    override fun getID(): Flow<Int?> = studentDatastore.getTeacherId()

    override fun getName(): Flow<String?> = studentDatastore.getUserName()

    override fun getPhone(): Flow<String?> = studentDatastore.getPhoneNo()

    override fun getToken(): Flow<String?> = studentDatastore.getToken()

    override fun getEmail(): Flow<String?> = studentDatastore.getEmail()
    override fun getPassword(): Flow<String?> = studentDatastore.getPassword()


}