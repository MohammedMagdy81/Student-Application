package com.example.studentapplication.ui.fragments.home.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.studentapplication.R
import com.example.studentapplication.data.remote.response.quizzes.GetAllQuizResponse
import com.example.studentapplication.databinding.FragmentQuizBinding
import com.example.studentapplication.utils.State
import com.example.studentapplication.utils.ViewsUtils.hideViews
import com.example.studentapplication.utils.ViewsUtils.showBottomNav
import com.example.studentapplication.utils.ViewsUtils.showViews
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private val viewModel by viewModels<QuizViewModel>()
    private lateinit var adapter: AllQuizzesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuizBinding.inflate(inflater)
        adapter = AllQuizzesAdapter(emptyList()) {
            showGoToQuizDialog {
                val action = QuizFragmentDirections.actionQuizFragmentToQuizQuestionsFragment2(it)
                findNavController().navigate(action)
            }




        }


        return binding.root
    }

    private fun showGoToQuizDialog(onGo: () -> Unit) {
        val dialog = AlertDialog.Builder(requireContext()).create()
        val view = layoutInflater.inflate(R.layout.dialog_logout, null)

        val btnConfirm = view.findViewById<Button>(R.id.btnConfirmLogout)
        btnConfirm.text = getString(R.string.go_to_quiz)

        val btnCancel = view.findViewById<Button>(R.id.btnCancelLogout)
        val textView = view.findViewById<TextView>(R.id.tvLogoutFromApp)
        textView.text = getString(R.string.sure_go_to_quiz)
        dialog.setView(view)

        btnConfirm.setOnClickListener {
            onGo.invoke()
            dialog.dismiss()
        }
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

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


}











