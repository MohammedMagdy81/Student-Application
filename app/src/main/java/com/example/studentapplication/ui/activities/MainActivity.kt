package com.example.studentapplication.ui.activities

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.studentapplication.R
import com.example.studentapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragmentContainerView
        ) as NavHostFragment

        navController = navHostFragment.navController

    }

    fun showLoadingScreen(message: String) {
        // 1 - set message
        binding.mainLoadingTv.text = message

        // 2 - set visibility
        binding.loadingLayout.visibility = View.VISIBLE

        // 3 - disable touch
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

    fun exitLoadingScreen() {
        // 1 - set visibility
        binding.loadingLayout.visibility = View.GONE
        // 2 - enable touch
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}