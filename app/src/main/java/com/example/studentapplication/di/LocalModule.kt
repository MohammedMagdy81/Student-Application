package com.example.studentapplication.di

import android.content.Context
import com.example.studentapplication.data.local.StudentDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun  provideStudentDatastore(@ApplicationContext context: Context):StudentDatastore =
        StudentDatastore(context)
}