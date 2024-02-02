package com.example.studentapplication.ui.fragments.settings.language

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studentapplication.R
import com.example.studentapplication.databinding.FragmentLanguageBinding
import com.example.studentapplication.ui.activities.HomeActivity
import com.example.studentapplication.utils.LocalHelper
import java.util.Locale

class LanguageFragment : Fragment() {

    private lateinit var binding: FragmentLanguageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLanguageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (Locale.getDefault().language) {
            "en" -> {
                changeToEnglish()
            }

            "ar" -> {
                changeToArabic()
            }
        }
        binding.linearArabic.setOnClickListener {
            changeLanguage("ar")
        }

        binding.linearEnglish.setOnClickListener {
            changeLanguage("en")
        }
    }

    private fun changeToArabic() {
        binding.apply {
            imgArabic.visibility = View.VISIBLE
            imgEnglish.visibility = View.INVISIBLE
        }
    }

    private fun changeToEnglish() {
        binding.apply {
            imgArabic.visibility = View.INVISIBLE
            imgEnglish.visibility = View.VISIBLE
        }
    }


    private fun changeLanguage(code: String) {
        val intent = Intent(requireActivity(), HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        val sharedPref = activity?.getSharedPreferences("Language", Context.MODE_PRIVATE)
        sharedPref?.edit()?.putString("language", "en")?.apply()
        if (code == "en") {
            LocalHelper.setLocale(requireActivity(), "en")
            changeToEnglish()
            sharedPref?.edit()?.putString("language", "en")?.apply()
            startActivity(intent)
        } else if (code == "ar") {
            LocalHelper.setLocale(requireActivity(), "ar")
            changeToArabic()
            sharedPref?.edit()?.putString("language", "ar")?.apply()
            startActivity(intent)
        }
    }


}