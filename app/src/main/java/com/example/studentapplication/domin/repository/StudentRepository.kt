package com.example.studentapplication.domin.repository

import kotlinx.coroutines.flow.Flow

interface StudentRepository {


    suspend fun setID(id:Int?)
    suspend fun setToken(token:String?)
    suspend fun setEmail(email:String?)
    suspend fun setPhone(phone:String?)
    suspend fun setName(name:String?)
    suspend fun setPassword(password:String?)

    fun getID(): Flow<Int?>
    fun getName(): Flow<String?>
    fun getPhone(): Flow<String?>
    fun getToken(): Flow<String?>
    fun getEmail(): Flow<String?>
    fun getPassword() :Flow<String?>
}