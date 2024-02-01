package com.example.studentapplication.ui.fragments.auth.signup

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.studentapplication.R
import com.example.studentapplication.data.local.preferences.ModelPreferencesManager
import com.example.studentapplication.data.remote.response.auth.RegisterResponse
import com.example.studentapplication.databinding.FragmentSignupBinding
import com.example.studentapplication.ui.activities.HomeActivity
import com.example.studentapplication.ui.activities.MainActivity
import com.example.studentapplication.utils.AuthValidations
import com.example.studentapplication.utils.Constants.STUDENT_KEY
import com.example.studentapplication.utils.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private val viewModel by viewModels<SignupViewModel>()

    //lateinit var arrayAdapter: ArrayAdapter<String>

    //private lateinit var className: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater)
//        arrayAdapter = ArrayAdapter(
//            requireContext(),
//            android.R.layout.simple_list_item_1,
//            resources.getStringArray(R.array.Stage_Names)
//        )
        binding.apply {
            etStage.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id ->
                    showStagesSpinner(position)
                }
            etStage.setOnClickListener {
                etStage.showDropDown()
            }
            btnSignup.setOnClickListener {
//                Log.d("SIGNUP_FRAGMENT", "Btn clicked")
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()
                val phone = etPhone.text.toString()
                val name = etFullName.text.toString()
                val address = etAddress.text.toString()

                viewModel.register(email, password, name, phone, address, getSerialNumber())
                observeToRegister()
            }

            observeToRegisterValidation()
            tvLogin.setOnClickListener {
                findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
            }
        }
        //binding.etStage.setAdapter(arrayAdapter);
        binding.etStage.isCursorVisible = false;

        return binding.root
    }

    @SuppressLint("HardwareIds")
    private fun getSerialNumber(): String {
        return Settings.Secure.getString(
            requireContext().contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }


    private fun observeToRegister() {
        val activity = requireActivity() as MainActivity
        lifecycleScope.launch {
            viewModel.registerLiveData.observe(viewLifecycleOwner) {
                when (it) {
                    is State.Failure -> {
                        activity.exitLoadingScreen()
                        Toast.makeText(
                            requireContext(), it.errorMessage,
                            Toast.LENGTH_LONG
                        ).show()

                    }

                    State.Loading -> {
                        activity.showLoadingScreen("جاري تسجيل الدخول ..")
                    }

                    is State.Success -> {
                        it.data?.let {
                            insertData(it)
                        }
                        activity.exitLoadingScreen()
                        goToHomeActivity()

                    }

                    else -> Unit
                }
            }
        }
    }

    private fun observeToRegisterValidation() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.registerFields.collect {
                if (it.email is AuthValidations.Error) {
                    withContext(Dispatchers.Main) {
                        binding.etEmail.requestFocus()
                        binding.etEmail.error = it.email.message
                    }
                }
                if (it.password is AuthValidations.Error) {
                    withContext(Dispatchers.Main) {
                        binding.etPassword.requestFocus()
                        binding.etPassword.error = it.password.message
                    }
                }
                if (it.name is AuthValidations.Error) {
                    withContext(Dispatchers.Main) {
                        binding.etFullName.requestFocus()
                        binding.etFullName.error = it.name.message
                    }
                }
                if (it.phone is AuthValidations.Error) {
                    withContext(Dispatchers.Main) {
                        binding.etPhone.requestFocus()
                        binding.etPhone.error = it.phone.message
                    }
                }
                if (it.address is AuthValidations.Error) {
                    withContext(Dispatchers.Main) {
                        binding.etAddress.requestFocus()
                        binding.etAddress.error = it.address.message
                    }
                }
            }
        }
    }

    private fun showStagesSpinner(position: Int) {
        binding.etStage.apply {
            showDropDown()
            //className = arrayAdapter.getItem(position)!!
        }

    }

    private fun goToHomeActivity() {
        val intent = Intent(requireActivity(), HomeActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }

    private fun insertData(teacher: RegisterResponse?) {
        viewLifecycleOwner.lifecycleScope.launch {
            teacher?.let { teacherResponse ->
                ModelPreferencesManager.put(teacherResponse, STUDENT_KEY)
            }
        }
    }

}