package com.example.studentapplication.ui.fragments.home.quiz

import com.example.studentapplication.data.remote.response.quizzes.QuestionsItem

interface IQuizClick {

    fun onQuizClick(questions: List<QuestionsItem?>?)
}