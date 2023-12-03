package com.example.studentapplication.ui.fragments.main.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.studentapplication.R
import com.example.studentapplication.databinding.FragmentLoginBinding
import com.example.studentapplication.ui.activities.HomeActivity
import com.example.studentapplication.ui.activities.MainActivity
import com.example.studentapplication.utils.AuthValidations
import com.example.studentapplication.utils.State
import com.example.studentapplication.utils.genericFunctions.resetPasswordDialog
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()


    private var userToken: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)
        binding.apply {
            tvSignup.setOnClickListener{
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


    private fun observeToResetPassword() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.resetPassword.observe(viewLifecycleOwner) {
                when (it) {
                    is State.Failure -> {
                        binding.loginSpinkit.visibility = View.GONE
                        Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_LONG).show()
                    }

                    is State.Loading -> {
                        binding.loginSpinkit.visibility = View.VISIBLE
                    }

                    is State.Success -> {
                        binding.loginSpinkit.visibility = View.GONE
                        Snackbar.make(
                            requireView(),
                            "تم تغيير كلمة السر بنجاح .. ",
                            Snackbar.LENGTH_LONG
                        ).show()

                    }

                    else -> Unit
                }
            }
        }
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
                        Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_LONG).show()
                    }

                    State.Loading -> {
                        activity.showLoadingScreen("جاري تسجيل الدخول ..")
                    }

                    is State.Success -> {
                        activity.exitLoadingScreen()
                        goToHomeActivity()

                    }

                    State.Ideal -> Unit
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

}












