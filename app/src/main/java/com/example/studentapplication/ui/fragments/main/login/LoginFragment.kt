package com.example.studentapplication.ui.fragments.main.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.studentapplication.R
import com.example.studentapplication.data.local.preferences.ModelPreferencesManager
import com.example.studentapplication.data.remote.response.auth.RegisterResponse
import com.example.studentapplication.databinding.FragmentLoginBinding
import com.example.studentapplication.ui.activities.HomeActivity
import com.example.studentapplication.ui.activities.MainActivity
import com.example.studentapplication.utils.AuthValidations
import com.example.studentapplication.utils.Constants.STUDENT_KEY
import com.example.studentapplication.utils.State
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)
        binding.apply {
            tvSignup.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
            }
            btnLogin.setOnClickListener {
                val email = etLoginEmail.text.toString()
                val password = etLoginPassword.text.toString()
                viewModel.login(email, password)
            }
            observeToLogin()
        }


        observeToLoginValidations()
        return binding.root
    }

    private fun observeToLoginValidations() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loginValidation.distinctUntilChanged().collect {
                if (it.email is AuthValidations.Error) {
                    withContext(Dispatchers.Main) {
                        binding.apply {
                            etLoginEmail.requestFocus()
                            etLoginEmail.error = it.email.message
                        }
                    }
                }
                if (it.password is AuthValidations.Error) {
                    withContext(Dispatchers.Main) {
                        binding.apply {
                            etLoginPassword.requestFocus()
                            etLoginPassword.error = it.password.message
                        }
                    }
                }
            }
        }
    }

    private fun observeToLogin() {
        val activity = requireActivity() as MainActivity
        lifecycleScope.launch {
            viewModel.loginResult.observe(viewLifecycleOwner) {
                when (it) {
                    is State.Failure -> {
                        activity.exitLoadingScreen()
                        Toasty.error(requireContext(), it.errorMessage, Toast.LENGTH_LONG).show()
                    }

                    State.Loading -> {
                        activity.showLoadingScreen("جاري تسجيل الدخول ..")
                    }

                    is State.Success -> {
                        if (it.data?.srialNumber != getSerialNumber()) {
                            activity.exitLoadingScreen()
                            Toasty.error(
                                requireContext(),
                                "لا يمكن تسجيل الدخول بنفس الايميل علي أكتر من جهاز !",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            it.data.apply {
                                val response = RegisterResponse(
                                    address,
                                    phone,
                                    name,
                                    isActive,
                                    srialNumber,
                                    email,
                                    token
                                )
                                insertData(response)
                            }

                            activity.exitLoadingScreen()
                            goToHomeActivity()
                        }

                    }
                    else -> {}
                }
            }
        }

    }

    private fun goToHomeActivity() {
        val intent = Intent(requireActivity(), HomeActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }

    @SuppressLint("HardwareIds")
    private fun getSerialNumber(): String {
        return Settings.Secure.getString(
            requireContext().contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }

    private fun insertData(teacher: RegisterResponse?) {
        viewLifecycleOwner.lifecycleScope.launch {
            teacher?.let { teacherResponse ->
                ModelPreferencesManager.put(teacherResponse, STUDENT_KEY)
            }
        }
    }

}












