package com.example.studentapplication.ui.fragments.auth.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.studentapplication.R
import com.example.studentapplication.databinding.FragmentOnboardingBinding


class OnboardingFragment : Fragment() {

    private lateinit var adapter: OnboardingAdapter
   private lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnboardingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layouts = intArrayOf(
            R.layout.layout_onboarding_1,
            R.layout.layout_onboarding_2,
            R.layout.layout_onboarding_3
        )
        adapter = OnboardingAdapter(requireContext(), layouts)
        binding.onBoardingPager.adapter = adapter
        binding.btnNext.setOnClickListener {
            val current = binding.onBoardingPager.currentItem + 1
            if (current < layouts.size) {
                binding.onBoardingPager.currentItem = current
            } else {
                findNavController().navigate(R.id.action_onboardingFragment_to_signupFragment)
            }
        }
    }



}