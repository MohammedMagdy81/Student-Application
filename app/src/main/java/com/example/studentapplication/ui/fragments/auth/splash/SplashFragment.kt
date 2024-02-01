package com.example.studentapplication.ui.fragments.auth.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.studentapplication.R
import com.example.studentapplication.data.local.preferences.ModelPreferencesManager
import com.example.studentapplication.data.remote.response.auth.RegisterResponse
import com.example.studentapplication.ui.activities.HomeActivity
import com.example.studentapplication.utils.Constants.STUDENT_KEY


class SplashFragment : Fragment() {


    @SuppressLint("HardwareIds")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val token = ModelPreferencesManager.get<RegisterResponse>(STUDENT_KEY)?.token
        if (token.isNullOrEmpty()) {
            goToIntroduction()
        } else {
            gotToHome()
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun gotToHome() {
        val intent = Intent(requireContext(), HomeActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
        }, 2000L)
    }

    private fun goToIntroduction() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_onboardingFragment)
        }, 2000L)
    }


}



