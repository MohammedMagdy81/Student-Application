package com.example.studentapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.studentapplication.BuildConfig
import com.example.studentapplication.StudentApp

object Network {
    fun isConnected(): Boolean { // this function check internet connection
        val result: Boolean
        val connectivityManager =
            StudentApp.appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> BuildConfig.DEBUG
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return result
    }
}