package com.example.studentapplication.ui.fragments.home.quiz

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapplication.data.local.preferences.ModelPreferencesManager
import com.example.studentapplication.data.remote.response.auth.RegisterResponse
import com.example.studentapplication.data.remote.response.quizzes.GetAllQuizResponse
import com.example.studentapplication.databinding.ItemAllQuizzesBinding
import com.example.studentapplication.utils.Constants.STUDENT_KEY

class AllQuizzesAdapter(
    private var quizzesList: List<GetAllQuizResponse>,
    private val quizClick: IQuizClick
) :
    RecyclerView.Adapter<AllQuizzesAdapter.AllQuizzesViewHolder>() {

    fun setItem(quizzesList: List<GetAllQuizResponse>) {
        this.quizzesList = quizzesList
        notifyDataSetChanged()
    }

    inner class AllQuizzesViewHolder(val binding: ItemAllQuizzesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentQuiz: GetAllQuizResponse) {
            val name = ModelPreferencesManager.get<RegisterResponse>(STUDENT_KEY)?.name
            val names = currentQuiz.studentsQuizzes?.map { it?.studentName }
            names?.let {
                binding.apply {
                    if (name in names) {
                        imgCheckMark.visibility = View.VISIBLE
                        tvQuizComplete.visibility = View.VISIBLE
                        constraint.isClickable = false
                        constraint.isEnabled = false
                        root.isEnabled = false
                        root.isClickable = false
                    } else {
                        imgCheckMark.visibility = View.GONE
                        tvQuizComplete.visibility = View.GONE
                    }

                }
            }
            binding.apply {
                quiName.text = currentQuiz.name
                quiSubject.text = currentQuiz.subjectName
                quiTime.text = "${currentQuiz.time} دقيقة "
                if (currentQuiz.questions!!.isEmpty()) {
                    quiQuestionsCount.text = "لا يوجد أسألة !"
                    quiQuestionsCount.setTextColor(Color.WHITE)
                    constraint.isClickable = false
                    constraint.isEnabled = false
                    root.isEnabled = false
                    root.isClickable = false
                } else {
                    quiQuestionsCount.text = "${currentQuiz.questions?.size} سؤال "
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllQuizzesViewHolder {
        val binding =
            ItemAllQuizzesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllQuizzesViewHolder(binding)
    }

    override fun getItemCount(): Int = quizzesList.size

    override fun onBindViewHolder(holder: AllQuizzesViewHolder, position: Int) {
        val currentQuiz = quizzesList[position]
        holder.bind(currentQuiz)
        holder.itemView.setOnClickListener {
            val questions = currentQuiz.questions
            quizClick.onQuizClick( questions)
        }
    }
}