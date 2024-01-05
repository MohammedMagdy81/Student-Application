package com.example.studentapplication.ui.fragments.home.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.studentapplication.data.local.preferences.ModelPreferencesManager
import com.example.studentapplication.data.remote.response.auth.RegisterResponse
import com.example.studentapplication.data.remote.response.quizzes.GetAllQuizResponse
import com.example.studentapplication.data.remote.response.quizzes.QuestionsItem
import com.example.studentapplication.databinding.FragmentQuizBinding
import com.example.studentapplication.utils.Constants
import com.example.studentapplication.utils.State
import com.example.studentapplication.utils.ViewsUtils.hideViews
import com.example.studentapplication.utils.ViewsUtils.showBottomNav
import com.example.studentapplication.utils.ViewsUtils.showViews
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class QuizFragment : Fragment(), IQuizClick {

    private lateinit var binding: FragmentQuizBinding
    private val viewModel by viewModels<QuizViewModel>()
    private val adapter by lazy {
        AllQuizzesAdapter(emptyList(), this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuizBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllQuizzes()
        viewModel.allQuizzes.observe(viewLifecycleOwner) {
            when (it) {
                is State.Failure -> {
                    binding.apply {
                        hideViews(
                            spinkit,
                            tvNoQuiz,
                            allQuizzesRv,
                            imageQuizNotFound,
                            tvNoFoundQuizDesc,
                            layoutNoInternet.root
                        )
                        showViews(layoutError.root)
                        layoutError.tvLayoutError.text = it.errorMessage

                    }

                }

                State.Loading -> {
                    binding.spinkit.visibility = View.VISIBLE
                    binding.apply {
                        hideViews(
                            tvNoQuiz,
                            allQuizzesRv,
                            imageQuizNotFound,
                            tvNoFoundQuizDesc,
                            layoutNoInternet.root,
                            layoutError.root
                        )


                    }

                }

                is State.NoInternet -> {
                    binding.apply {
                        hideViews(
                            spinkit,
                            tvNoQuiz,
                            allQuizzesRv,
                            imageQuizNotFound,
                            tvNoFoundQuizDesc,
                            layoutError.root
                        )
                        showViews(layoutNoInternet.root)
                    }
                }

                is State.Success -> {
                    if (it.data!!.isEmpty()) {
                        binding.apply {
                            hideViews(
                                layoutError.root,
                                layoutNoInternet.root,
                                spinkit,
                                allQuizzesRv,
                            )
                            showViews(tvNoQuiz, tvNoFoundQuizDesc, imageQuizNotFound)
                        }
                    } else {
                        binding.apply {
                            hideViews(
                                layoutError.root,
                                layoutNoInternet.root,
                                spinkit,
                                tvNoQuiz,
                                tvNoFoundQuizDesc,
                                imageQuizNotFound
                            )
                            showViews(allQuizzesRv)
                            setupData(it.data)
                        }
                    }
                }
            }
        }
    }

    private fun setupData(data: List<GetAllQuizResponse>) {
        adapter.setItem(data)
        binding.allQuizzesRv.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        showBottomNav()
    }

    override fun onQuizClick(questions: List<QuestionsItem?>?) {


    }


}











