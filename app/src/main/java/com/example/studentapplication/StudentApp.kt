package com.example.studentapplication

import android.app.Application
import android.content.Context
import com.example.studentapplication.data.local.preferences.ModelPreferencesManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StudentApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        ModelPreferencesManager.with(this)
    }
    companion object {
        lateinit var appContext: Context
    }


}