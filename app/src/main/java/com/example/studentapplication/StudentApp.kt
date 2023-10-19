package com.example.studentapplication

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StudentApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
    companion object {
        lateinit var appContext: Context
    }

}