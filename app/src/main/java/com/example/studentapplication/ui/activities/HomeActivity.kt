package com.example.studentapplication.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.studentapplication.R
import com.example.studentapplication.databinding.ActivityHomeBinding
import com.example.studentapplication.ui.fragments.home.homePage.HomeFragment
import com.example.studentapplication.ui.fragments.home.lectures.LecturesFragment
import com.example.studentapplication.ui.fragments.home.quiz.QuizFragment
import com.example.studentapplication.ui.fragments.home.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragmentHomeContainerView
        ) as NavHostFragment

        navController = navHostFragment.navController

        binding.bottomNavigation.setOnItemSelectedListener { itemId ->
            when (itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragmentDis)
                }
                R.id.quizFragment -> {
                    navController.navigate(R.id.quizFragment)
                }
                R.id.lecturesFragment -> {
                    navController.navigate(R.id.lecturesFragment)
                }
                R.id.settingsFragment -> {
                    navController.navigate(R.id.settingsFragment)
                }

            }
            true
        }
        binding.bottomNavigation.setItemSelected(R.id.homeFragment, true)
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

    fun setHomeSelected() = binding.bottomNavigation.setItemSelected(R.id.homeFragment, true)
    fun exitLoadingScreen() {
        // 1 - set visibility
        binding.loadingLayout.visibility = View.GONE
        // 2 - enable touch
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}