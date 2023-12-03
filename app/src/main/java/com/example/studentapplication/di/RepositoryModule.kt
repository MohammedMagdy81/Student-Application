package com.example.studentapplication.di

import com.example.studentapplication.data.remote.ApiService
import com.example.studentapplication.data.repository.AuthRepositoryImpl
import com.example.studentapplication.data.repository.LecturesRepositoryImpl
import com.example.studentapplication.domin.repository.AuthRepository
import com.example.studentapplication.domin.repository.LecturesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    fun provideAuthRepository(apiService: ApiService): AuthRepository =
        AuthRepositoryImpl(apiService)

    @Provides
    fun provideLecturesRepository(apiService: ApiService): LecturesRepository =
        LecturesRepositoryImpl(apiService)


}