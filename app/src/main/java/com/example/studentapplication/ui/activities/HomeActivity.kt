package com.example.studentapplication.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
                    navController.navigate(R.id.homeFragment)
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
        }
    }
}