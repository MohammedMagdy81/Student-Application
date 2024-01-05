package com.example.studentapplication.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
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
    val fragment = HomeFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setItemSelected(R.id.homeFragment)
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it) {
                R.id.homeFragment -> {
                    openHomePage()
                }

                R.id.quizFragment -> {
                    val quizFragment = QuizFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, quizFragment).commit()

                }

                R.id.lecturesFragment -> {
                    val lecturesFragment = LecturesFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, lecturesFragment).commit()

                }

                R.id.settingsFragment -> {
                    val settingsFragment = SettingsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, settingsFragment).commit()

                }

            }
        }

    }

    private fun openHomePage() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.commit()
    }
}