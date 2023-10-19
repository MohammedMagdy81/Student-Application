package com.example.studentapplication.ui.fragments.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.studentapplication.R
import com.example.studentapplication.databinding.FragmentEditProfileBinding
import com.example.studentapplication.ui.fragments.shared.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

@AndroidEntryPoint
class EditProfileFragment : Fragment() {
//val userId: RequestBody = userId.toRequestBody("multipart/formdata".toMediaTypeOrNull())

    private lateinit var binding: FragmentEditProfileBinding
    private val viewModel by viewModels<EditProfileViewModel>()
    private val mainViewModel by viewModels<MainViewModel>()

    private var userToken: String = ""
    private var oldPassword:String = ""
    private lateinit var name:RequestBody
    private lateinit var email:RequestBody
    private lateinit var phone:RequestBody
    private lateinit var newPassword:RequestBody

    private fun initData() {
        name = binding.etFullName.text.toString().toRequestBody("multipart/formdata".toMediaTypeOrNull())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditProfileBinding.inflate(inflater)
        initData()
        lifecycleScope.launch {
            mainViewModel.getToken().collect{
                if (it != null) {
                    userToken = it
                }
            }
            mainViewModel
        }


        binding.apply {
            btnSaveChanges.setOnClickListener {
                viewModel.updateProfile(token = userToken, name = )
            }
        }
        return binding.root
    }


}