package com.example.studentapplication.ui.fragments.home.quizQuestions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapplication.R
import com.example.studentapplication.data.remote.response.quizzes.QuestionsItem
import com.example.studentapplication.databinding.ItemQuestionQuizBinding
import com.squareup.picasso.Picasso

class QuizQuestionsAdapter(

) : RecyclerView.Adapter<QuizQuestionsAdapter.QuizQuestionsViewHolder>() {

    var selectAnswer: ISelectAnswer? = null

    private val shuffledAnswersMap = mutableMapOf<Int, List<String>>()

    inner class QuizQuestionsViewHolder(val itemBinding: ItemQuestionQuizBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        private val cardViews = listOf(
            itemBinding.constraint1,
            itemBinding.constraint2,
            itemBinding.constraint3,
            itemBinding.constraint4
        )

        private val checkBoxes = listOf(
            itemBinding.checkbox1,
            itemBinding.checkbox2,
            itemBinding.checkbox3,
            itemBinding.checkbox4
        )


        private val textViews = listOf(
            itemBinding.textView1,
            itemBinding.textView2,
            itemBinding.textView3,
            itemBinding.textView4
        )

        fun bind(currentQuestion: QuestionsItem?, position: Int) {
            itemBinding.apply {
                currentQuestion?.let { question ->
                    tvQuestion.text = question.name
                    if (currentQuestion.url!=null){
                        imageQuestion.visibility= View.VISIBLE
                        Picasso.get()
                            .load(question.url)
                            .fit()
                            .centerCrop()
                            .placeholder(R.drawable.lectures)
                            .error(R.drawable.ic_error2)
                            .into(imageQuestion)
                    }else {
                        imageQuestion.visibility= View.GONE
                    }
                    val answers = mutableListOf(
                        question.wrongAnswers,
                        question.wrongAnswers2,
                        question.wrongAnswers3,
                        question.correctAnwer
                    )


                    textView1.text = answers[0]
                    textView2.text = answers[1]
                    textView3.text = answers[2]
                    textView4.text = answers[3]

                    // Set checkbox states and card background
                    checkBoxes.forEachIndexed { index, checkBox ->
                        checkBox.isChecked = question.checkedPosition == index
                        updateCardBackground(cardViews[index], question.checkedPosition == index)
                    }

                    // Set click listener for checkboxes
                    checkBoxes.forEachIndexed { index, checkBox ->
                        checkBox.setOnClickListener {
                            val updatedQuestion = currentQuestion.copy(checkedPosition = index)
                            val selectedTextView = textViews[index]
                            selectAnswer?.selectAnswer(
                                StudentAnswer(
                                    questionsId = currentQuestion.id!!,
                                    correctAnswer = selectedTextView.text.toString(),
                                    questionDegree = currentQuestion.grade!!
                                )
                            )
                            val newList = differ.currentList.toMutableList()
                            newList[position] = updatedQuestion
                            differ.submitList(newList)
                        }
                    }
                }
            }
        }

        private fun updateCardBackground(cardView: ConstraintLayout, isSelected: Boolean) {
            val color = if (isSelected) {
                ContextCompat.getColor(itemView.context, R.color.light_gray)
            } else {
                ContextCompat.getColor(itemView.context, android.R.color.white)
            }
            cardView.setBackgroundColor(color)
        }
    }

    var diffUtil = object : DiffUtil.ItemCallback<QuestionsItem>() {

        override fun areItemsTheSame(oldItem: QuestionsItem, newItem: QuestionsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: QuestionsItem, newItem: QuestionsItem): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizQuestionsViewHolder {
        val itemBinding = ItemQuestionQuizBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return QuizQuestionsViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: QuizQuestionsViewHolder, position: Int) {
        val currentQuestion = differ.currentList[position]
        holder.bind(currentQuestion, position)
    }
}