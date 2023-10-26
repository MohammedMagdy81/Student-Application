package com.example.studentapplication.ui.fragments.main.notifications

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studentapplication.R
import com.example.studentapplication.databinding.FragmentNotificationBinding
import com.example.studentapplication.ui.activities.HomeActivity


class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotificationBinding.inflate(inflater)
        binding.apply {
            btnAllowNotifications.setOnClickListener {
               allowToSendNotifications()
            }
            btnSkipNotifications.setOnClickListener {
                goToHomeActivity()
            }

        }
        return binding.root
    }

    private fun allowToSendNotifications() {

    }

    private fun goToHomeActivity() {
        val intent = Intent(requireActivity(), HomeActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }

}