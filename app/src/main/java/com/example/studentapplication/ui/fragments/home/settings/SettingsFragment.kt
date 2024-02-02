package com.example.studentapplication.ui.fragments.home.settings

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.studentapplication.R
import com.example.studentapplication.data.local.preferences.ModelPreferencesManager
import com.example.studentapplication.databinding.FragmentSettingsBinding
import com.example.studentapplication.ui.activities.MainActivity
import com.example.studentapplication.utils.ViewsUtils.showBottomNav

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater)
        binding.apply {
            tvlogout.setOnClickListener {
                showLogoutDialog {
                    ModelPreferencesManager.deletePref()
                    val intent = Intent(requireActivity(), MainActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    startActivity(intent)
                }
            }
            tvLanguage.setOnClickListener {
                findNavController().navigate(R.id.action_settingsFragment_to_languageFragment)
            }
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        showBottomNav()
    }

    @SuppressLint("MissingInflatedId")
    private fun showLogoutDialog(onLogout: () -> Unit) {
        val dialog = AlertDialog.Builder(requireContext()).create()
        val view = layoutInflater.inflate(R.layout.dialog_logout, null)

        val btnConfirm = view.findViewById<Button>(R.id.btnConfirmLogout)
        val btnCancel = view.findViewById<Button>(R.id.btnCancelLogout)
        dialog.setView(view)

        btnConfirm.setOnClickListener {
            onLogout.invoke()
            dialog.dismiss()
        }
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }

}