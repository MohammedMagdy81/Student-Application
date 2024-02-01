package com.example.studentapplication.ui.fragments.home.quizQuestions

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.studentapplication.R
import com.example.studentapplication.data.local.preferences.ModelPreferencesManager
import com.example.studentapplication.data.remote.response.auth.RegisterResponse
import com.example.studentapplication.data.remote.response.quizzes.QuestionsItem
import com.example.studentapplication.databinding.FragmentQuizQuestionsBinding
import com.example.studentapplication.domin.model.StudentQuizBody
import com.example.studentapplication.ui.activities.HomeActivity
import com.example.studentapplication.ui.activities.MainActivity
import com.example.studentapplication.utils.Constants.STUDENT_KEY
import com.example.studentapplication.utils.State
import com.example.studentapplication.utils.ViewsUtils.submitAnswersQuizDialog
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlin.math.log


@AndroidEntryPoint
class QuizQuestionsFragment : Fragment() {
    private lateinit var binding: FragmentQuizQuestionsBinding
    private val adapter by lazy { QuizQuestionsAdapter() }


    private var studentName = ""
    private var studentPhone = ""

    private val viewModel by viewModels<AddStudentQuizViewModel>()

    var studentDegree = 0

    private var questionsAnswerMap = HashMap<Int, StudentAnswer>()

    private val quiz: QuizQuestionsFragmentArgs by navArgs()


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
        studentName = ModelPreferencesManager.get<RegisterResponse>(STUDENT_KEY)?.name.toString()
        studentPhone = ModelPreferencesManager.get<RegisterResponse>(STUDENT_KEY)?.phone.toString()

        setUpQuestions(quiz.quiz.questions)
        adapter.selectAnswer = object : ISelectAnswer {
            override fun selectAnswer(selectedAnswer: StudentAnswer) {
                addStudentAnswer(selectedAnswer)
                Log.d("SELECT ANSWER", "$questionsAnswerMap")
            }

        }
        binding.btnSubmitQuiz.setOnClickListener {
            submitAnswersQuizDialog {
                calculateResults()
            }

        }
        return binding.root
    }

    private fun observeToAddStudentQuiz() {
        val activity = requireActivity() as HomeActivity
        viewModel.addStudentQuiz.observe(viewLifecycleOwner) {
            when (it) {
                is State.Failure -> {
                    activity.exitLoadingScreen()
                    Toasty.error(requireContext(), it.errorMessage, Toast.LENGTH_LONG).show()
                }

                State.Loading -> {
                    activity.showLoadingScreen(getString(R.string.submit_answers))
                }

                is State.NoInternet -> {
                    activity.exitLoadingScreen()
                    Toasty.error(requireContext(), it.errorMessage, Toast.LENGTH_LONG).show()
                }

                is State.Success -> {
                    activity.exitLoadingScreen()
                    Toasty.success(
                        requireContext(),
                        getString(R.string.quiz_submitted_successfully),
                        Toast.LENGTH_LONG
                    ).show()
                    findNavController().popBackStack(R.id.homeFragmentDis, false)
                    activity.setHomeSelected()
                }
            }
        }
    }

    private fun setUpQuestions(questions: List<QuestionsItem?>?) {
        binding.apply {
            adapter.differ.submitList(questions)
            questionsRv.adapter = adapter
        }
    }

    fun addStudentAnswer(studentAnswer: StudentAnswer) {
        questionsAnswerMap[studentAnswer.questionsId] = studentAnswer
    }

    private fun calculateResults() {
        val questionsList = adapter.differ.currentList // Get the list of questions
        val studentAnswersList =
            questionsAnswerMap.values.toList() // Get the list of student answers

        var totalDegree = 0
        var obtainedDegree = 0

        for (question in questionsList) {
            val studentAnswer = studentAnswersList.find { it.questionsId == question.id }

            if (studentAnswer != null) {
                val isAnswerCorrect = studentAnswer.correctAnswer == question.correctAnwer
                if (isAnswerCorrect) {
                    obtainedDegree += question.grade ?: 0
                }
                totalDegree += question.grade ?: 0
            }
        }
        val addStudentQuizBody = StudentQuizBody(
            studentId = 1,
            studentName = studentName,
            studentDegree = obtainedDegree,
            quizId = quiz.quiz.id,
            quizName = quiz.quiz.name,
            time = 15,
            phoneNumber = studentPhone
        )
        viewModel.addStudentQuiz(addStudentQuizBody)
        observeToAddStudentQuiz()
    }


}