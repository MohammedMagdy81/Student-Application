package com.example.studentapplication.utils

import com.example.studentapplication.data.remote.response.quizzes.QuestionsItem

object Constants {
    const val BASE_URL = "https://18af-45-243-109-188.ngrok-free.app/"
    const val STUDENT_KEY = "StudentKey"

    fun QuestionsItem.generateShuffledAnswers(): List<String> {
        val answers = listOf(wrongAnswers, wrongAnswers2, wrongAnswers3, correctAnwer).shuffled()
        return answers.mapNotNull { it } // Exclude null answers if any
    }

}
