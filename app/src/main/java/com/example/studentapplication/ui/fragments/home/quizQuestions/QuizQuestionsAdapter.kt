package com.example.studentapplication.ui.fragments.home.quizQuestions

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapplication.R
import com.example.studentapplication.data.remote.response.quizzes.QuestionsItem
import com.example.studentapplication.databinding.ItemQuestionQuizBinding

//class QuizQuestionsAdapter : RecyclerView.Adapter<QuizQuestionsAdapter.QuizQuestionsViewHolder>() {
//
//    private var selectedPosition = RecyclerView.NO_POSITION
//
//    inner class QuizQuestionsViewHolder(val itemBinding: ItemQuestionQuizBinding) :
//        RecyclerView.ViewHolder(itemBinding.root) {
//
//        private val cardViews: List<CardView> = listOf(
//            itemBinding.card1,
//            itemBinding.card2,
//            itemBinding.card3,
//            itemBinding.card4
//        )
//
//        private val checkBoxes: List<CheckBox> = listOf(
//            itemBinding.checkbox1,
//            itemBinding.checkbox2,
//            itemBinding.checkbox3,
//            itemBinding.checkbox4
//        )
//
//
//        fun bind(currentQuestion: QuestionsItem?, position: Int) {
//            itemBinding.apply {
//                currentQuestion?.let { question ->
//
//
//                    tvQuestion.text = question.name
//
//                    textView1.text = question.wrongAnswers
//                    textView2.text = question.wrongAnswers2
//                    textView3.text = question.wrongAnswers3
//                    textView4.text = question.correctAnwer
//
//                }
//            }
//
//
//        }
//
//
//
//    }
//
//    var diffUtil = object : DiffUtil.ItemCallback<QuestionsItem>() {
//
//        override fun areItemsTheSame(oldItem: QuestionsItem, newItem: QuestionsItem): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: QuestionsItem, newItem: QuestionsItem): Boolean {
//            return oldItem == newItem
//        }
//    }
//    var differ = AsyncListDiffer(this, diffUtil)
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizQuestionsViewHolder {
//        val itemBinding = ItemQuestionQuizBinding.inflate(
//            LayoutInflater.from(parent.context),
//            parent, false
//        )
//        return QuizQuestionsViewHolder(itemBinding)
//    }
//
//    override fun getItemCount(): Int = differ.currentList.size
//
//    override fun onBindViewHolder(holder: QuizQuestionsViewHolder, position: Int) {
//        val currentQuestion = differ.currentList[position]
//        holder.bind(currentQuestion, position)
//    }
//
//
//}
class QuizQuestionsAdapter : RecyclerView.Adapter<QuizQuestionsAdapter.QuizQuestionsViewHolder>() {

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

        fun bind(currentQuestion: QuestionsItem?, position: Int) {
            itemBinding.apply {
                currentQuestion?.let { question ->
                    tvQuestion.text = question.name
                    textView1.text = question.wrongAnswers
                    textView2.text = question.wrongAnswers2
                    textView3.text = question.wrongAnswers3
                    textView4.text = question.correctAnwer

                    // Set checkbox states and card background
                    checkBoxes.forEachIndexed { index, checkBox ->
                        checkBox.isChecked = question.checkedPosition == index
                        updateCardBackground(cardViews[index], question.checkedPosition == index)
                    }

                    // Set click listener for checkboxes
                    checkBoxes.forEachIndexed { index, checkBox ->
                        checkBox.setOnClickListener {

                            val updatedQuestion = currentQuestion.copy(checkedPosition = index)

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