package com.example.studentapplication.di

import com.example.studentapplication.domin.repository.AuthRepository
import com.example.studentapplication.domin.repository.StudentRepository
import com.example.studentapplication.domin.use_cases.auth.AuthUseCases
import com.example.studentapplication.domin.use_cases.auth.LoginUseCase
import com.example.studentapplication.domin.use_cases.auth.RegisterUseCase
import com.example.studentapplication.domin.use_cases.auth.ResetPasswordUseCase
import com.example.studentapplication.domin.use_cases.datastore.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun provideAuthUseCases(authRepository: AuthRepository): AuthUseCases =
        AuthUseCases(
            registerUseCase = RegisterUseCase(authRepository),
            loginUseCase = LoginUseCase(authRepository) ,
            resetPasswordUseCase = ResetPasswordUseCase(authRepository)
        )

    @Provides
    @Singleton
    fun provideDatastoreUseCases(studentRepository: StudentRepository): DatastoreUseCases =
        DatastoreUseCases(
            setIDUseCase = SetIDUseCase(studentRepository),
            setEmailUseCase = SetEmailUseCase(studentRepository),
            setTokenUseCase = SetTokenUseCase(studentRepository),
            setNameUseCase = SetUserNameUseCase(studentRepository),
            setPhoneUseCase = SetPhoneUseCase(studentRepository),
            getIDUseCase = GetIDUseCase(studentRepository),
            getEmailUseCase = GetEmailUseCase(studentRepository),
            getNameUseCase = GetNameUseCase(studentRepository),
            getPhoneUseCase = GetPhoneUseCase(studentRepository),
            getTokenUseCase = GetTokenUseCase(studentRepository)
        )
}