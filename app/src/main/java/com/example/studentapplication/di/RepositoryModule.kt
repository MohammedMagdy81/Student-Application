package com.example.studentapplication.di

import com.example.studentapplication.data.local.StudentDatastore
import com.example.studentapplication.data.remote.ApiService
import com.example.studentapplication.data.repository.AuthRepositoryImpl
import com.example.studentapplication.data.repository.ProfileRepositoryImpl
import com.example.studentapplication.data.repository.StudentRepositoryImpl
import com.example.studentapplication.domin.repository.AuthRepository
import com.example.studentapplication.domin.repository.ProfileRepository
import com.example.studentapplication.domin.repository.StudentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(apiService: ApiService): AuthRepository =
        AuthRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideStudentRepository(studentDatastore: StudentDatastore):StudentRepository =
        StudentRepositoryImpl(studentDatastore)

    @Provides
    @Singleton
    fun provideProfileRepository(apiService: ApiService):ProfileRepository =
        ProfileRepositoryImpl(apiService)
}