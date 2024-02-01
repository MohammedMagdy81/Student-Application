package com.example.studentapplication.ui.fragments.home.quiz

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapplication.data.local.preferences.ModelPreferencesManager
import com.example.studentapplication.data.remote.response.auth.RegisterResponse
import com.example.studentapplication.data.remote.response.quizzes.GetAllQuizResponse
import com.example.studentapplication.data.remote.response.quizzes.QuestionsItem
import com.example.studentapplication.databinding.ItemAllQuizzesBinding
import com.example.studentapplication.utils.Constants.STUDENT_KEY
import com.example.studentapplication.utils.ViewsUtils.setTextColor

class AllQuizzesAdapter(
    private var quizzesList: List<GetAllQuizResponse?>?,
    var onItemClick: ((GetAllQuizResponse) -> Unit)? = null

) :
    RecyclerView.Adapter<AllQuizzesAdapter.AllQuizzesViewHolder>() {

    fun setItem(quizzesList: List<GetAllQuizResponse>) {
        this.quizzesList = quizzesList
        notifyDataSetChanged()
    }

    inner class AllQuizzesViewHolder(val binding: ItemAllQuizzesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentQuiz: GetAllQuizResponse?) {
            val name = ModelPreferencesManager.get<RegisterResponse>(STUDENT_KEY)?.name
            val phone = ModelPreferencesManager.get<RegisterResponse>(STUDENT_KEY)?.phone
            val names = currentQuiz?.studentsQuizzes?.map { it?.studentName }
            val phones = currentQuiz?.studentsQuizzes?.map { it?.phoneNumber }
            names?.let {
                binding.apply {
                    if (phones != null) {
                        if (name in names && phone in phones) {
                            imgCheckMark.visibility = View.VISIBLE
                            tvQuizComplete.visibility = View.VISIBLE

                            constraint.isClickable = false
                            constraint.isEnabled = false
                            root.isEnabled = false
                            root.isClickable = false
                            setTextColor(
                                tvQuizName,
                                quiName,
                                tvQuizTime,
                                quiTime,
                                tvQuizSubject,
                                quiSubject,
                                tvQuizQuestionsCount,
                                quiQuestionsCount,
                                tvQuizComplete
                            )
                        } else {
                            imgCheckMark.visibility = View.GONE
                            tvQuizComplete.visibility = View.GONE
                        }
                    }

                }
            }
            binding.apply {
                quiName.text = currentQuiz?.name
                quiSubject.text = currentQuiz?.subjectName
                quiTime.text = "${currentQuiz?.time} دقيقة "
                if (currentQuiz?.questions!!.isEmpty()) {
                    quiQuestionsCount.text = "لا يوجد أسألة !"
                    quiQuestionsCount.setTextColor(Color.WHITE)
                    constraint.isClickable = false
                    constraint.isEnabled = false
                    setTextColor(
                        tvQuizName,
                        quiName,
                        tvQuizTime,
                        quiTime,
                        tvQuizSubject,
                        quiSubject,
                        tvQuizQuestionsCount,
                        quiQuestionsCount
                    )
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


    override fun getItemCount(): Int = quizzesList?.size ?: 0

    override fun onBindViewHolder(holder: AllQuizzesViewHolder, position: Int) {
        val currentQuiz = quizzesList?.get(position)
        holder.bind(currentQuiz)
        holder.binding.root.setOnClickListener {
            if (currentQuiz != null)
                onItemClick?.invoke(currentQuiz)
        }

    }
}