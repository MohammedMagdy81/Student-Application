package com.example.studentapplication.ui.fragments.home.homePage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.studentapplication.R
import com.example.studentapplication.data.local.preferences.ModelPreferencesManager
import com.example.studentapplication.databinding.FragmentHomeBinding
import com.example.studentapplication.utils.Constants.EMAIL_KEY
import com.example.studentapplication.utils.Constants.TOKEN_KEY
import com.example.studentapplication.utils.State
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<LecturesViewModel>()
    private var email: String = ""
    private var token: String = ""
    private val allLecturesAdapter by lazy {
        AllLecturesAdapter(emptyList()) { item ->
            item.accessEmails.onEach {
                if (it.emailAccsess != email) {
                    Toasty.error(
                        requireContext(),
                        "عفوا غير مسموح لك بمشاهدة هذه المحاضرة !",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    val direction =
                        HomeFragmentDirections.actionHomeFragmentToLectureDetailFragment(item)
                    findNavController().navigate(direction)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ModelPreferencesManager.get<String>(EMAIL_KEY)?.let {
            email = it
        }
        ModelPreferencesManager.get<String>(TOKEN_KEY)?.let {
            token = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllLectures(token = token)
        viewModel.allLecturesLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is State.Failure -> {
                    binding.etHomeSearch.visibility = View.GONE
                    binding.spinKit.visibility = View.GONE
                    Toasty.error(requireContext(), it.errorMessage, Toast.LENGTH_LONG).show()
                }

                State.Ideal -> {

                }

                State.Loading -> {
                    binding.spinKit.visibility = View.VISIBLE
                }

                is State.Success -> {
                    binding.etHomeSearch.visibility = View.VISIBLE
                    binding.spinKit.visibility = View.GONE
                    allLecturesAdapter.setItems(it.data)
                    binding.rvHomeLectures.adapter = allLecturesAdapter
                }
            }
        }
    }


}










