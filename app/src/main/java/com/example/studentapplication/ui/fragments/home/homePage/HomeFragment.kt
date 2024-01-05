package com.example.studentapplication.ui.fragments.home.homePage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.studentapplication.R
import com.example.studentapplication.data.local.preferences.ModelPreferencesManager
import com.example.studentapplication.data.remote.response.auth.RegisterResponse
import com.example.studentapplication.databinding.FragmentHomeBinding
import com.example.studentapplication.utils.Constants.STUDENT_KEY
import com.example.studentapplication.utils.State
import com.example.studentapplication.utils.ViewsUtils.hideViews
import com.example.studentapplication.utils.ViewsUtils.showBottomNav
import com.example.studentapplication.utils.ViewsUtils.showViews
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<LecturesViewModel>()

    private var email: String = ""
    private var token = ""

    private val allLecturesAdapter by lazy {
        AllLecturesAdapter(emptyList()) { item ->
            val emails = item.accessEmails.map { it.emailAccsess }
            if (email in emails) {
                showValidateCodeDialog { code ->
                    val access = item.accessEmails.find { it.emailAccsess == email }
                    access?.let {
                        if (it.codeAccess == code) {
                            Toasty.success(
                                requireContext(),
                                "حسنا الكود صحيح ..",
                                Toast.LENGTH_LONG
                            ).show()
                            val action =
                                HomeFragmentDirections.actionHomeFragmentToLectureDetailFragment(
                                    item
                                )
                            findNavController().navigate(action)
                        } else {
                            Toasty.error(
                                requireContext(),
                                "للأسف ! الكود غير مطابق ..",
                                Toast.LENGTH_LONG
                            ).show()

                        }
                    }
                }
            } else {
                Toasty.error(
                    requireContext(),
                    "عفوا غير مسموح لك بمشاهدة هذه المحاضرة !",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        email = ModelPreferencesManager.get<RegisterResponse>(
            STUDENT_KEY
        )?.email.toString()
        token = ModelPreferencesManager.get<RegisterResponse>(
            STUDENT_KEY
        )?.token.toString()

        binding.tvHomeHeader.text = "Hi , ${
            ModelPreferencesManager.get<RegisterResponse>(
                STUDENT_KEY
            )?.name
        }"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllLectures(token = token)
        viewModel.allLecturesLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is State.Failure -> {
                    binding.apply {
                        showViews(tvHomeHeader, tvHomeHeader2, layoutSearch, tvLectures)
                    }
                    binding.etHomeSearch.visibility = View.GONE
                    binding.spinKit.visibility = View.GONE
                    Toasty.error(requireContext(), it.errorMessage, Toast.LENGTH_LONG).show()
                }

                State.Loading -> {
                    binding.apply {
                        hideViews(tvHomeHeader, tvHomeHeader2, layoutSearch, tvLectures)
                    }
                    binding.spinKit.visibility = View.VISIBLE
                }

                is State.Success -> {

                    if (it.data!!.isEmpty()) {
                        binding.spinKit.visibility = View.GONE
                        binding.apply {
                            showViews(
                                tvHomeHeader,
                                tvHomeHeader2,
                                layoutSearch,
                                tvLectures,
                                layoutEmpty.root
                            )
                        }
                    } else {
                        binding.apply {
                            showViews(tvHomeHeader, tvHomeHeader2, layoutSearch, tvLectures)
                        }
                        Log.d("HomeResponse", "onViewCreated: ${it.data}")
                        binding.etHomeSearch.visibility = View.VISIBLE
                        binding.spinKit.visibility = View.GONE
                        allLecturesAdapter.setItems(it.data)
                        binding.rvHomeLectures.adapter = allLecturesAdapter
                    }
                }


                is State.NoInternet -> {
                    binding.apply {
                        hideViews(
                            rvHomeLectures,
                            tvLectures,
                            tvHomeHeader,
                            tvHomeHeader2,
                            layoutSearch
                        )
                        layoutNoInternet.root.visibility = View.VISIBLE
                    }
                }

                else -> {}
            }
        }
    }


    override fun onStart() {
        super.onStart()
        showBottomNav()
    }

    private fun showValidateCodeDialog(onCodeIsCorrect: (String) -> Unit) {
        val dialog = AlertDialog.Builder(requireContext()).create()
        val view = layoutInflater.inflate(R.layout.dialog_lecture, null)


        val etCode = view.findViewById<EditText>(R.id.etCode)
        val btnConfirm = view.findViewById<Button>(R.id.btnConfirmCode)
        val btnCancel = view.findViewById<Button>(R.id.btnCancelCode)
        dialog.setView(view)

        btnConfirm.setOnClickListener {
            if (etCode.text.isEmpty()) {
                etCode.requestFocus()
                etCode.error = "أدخل كود المحاضرة !"
            } else {
                onCodeIsCorrect.invoke(etCode.text.toString())
                dialog.dismiss()
            }

        }
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }

}










