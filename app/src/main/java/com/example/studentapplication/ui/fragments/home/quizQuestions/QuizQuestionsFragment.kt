package com.example.studentapplication.ui.fragments.home.quizQuestions

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.studentapplication.R
import com.example.studentapplication.data.remote.response.quizzes.QuestionsItem
import com.example.studentapplication.databinding.FragmentQuizQuestionsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class QuizQuestionsFragment : Fragment() {
    private lateinit var binding: FragmentQuizQuestionsBinding
    private val adapter by lazy { QuizQuestionsAdapter() }

    private val quiz: QuizQuestionsFragmentArgs by navArgs<QuizQuestionsFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuizQuestionsBinding.inflate(inflater)
        quiz.quiz.time?.let {
            binding.countdownView.start(it * 60 * 1000L)
        }
        quiz.quiz.name?.let {
            binding.tvQuizName.text = it
        }
        setUpQuestions(quiz.quiz.questions)
        return binding.root
    }

    private fun setUpQuestions(questions: List<QuestionsItem?>?) {
        binding.apply {
            adapter.differ.submitList(questions)
            questionsRv.adapter = adapter
        }
    }


}