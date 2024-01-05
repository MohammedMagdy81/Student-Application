package com.example.studentapplication.ui.fragments.home.questionsQuiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studentapplication.R
import com.example.studentapplication.databinding.FragmentQuestionsQuizBinding

class QuestionsQuizFragment : Fragment() {

    private lateinit var binding: FragmentQuestionsQuizBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionsQuizBinding.inflate(inflater)
        return binding.root
    }


}