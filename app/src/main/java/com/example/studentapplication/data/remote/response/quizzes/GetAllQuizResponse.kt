package com.example.studentapplication.data.remote.response.quizzes

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetAllQuizResponse(

    @field:SerializedName("studentsQuizzes")
    val studentsQuizzes: List<StudentsQuizzes?>? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("questions")
    val questions: List<QuestionsItem?>? = null,


    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("time")
    val time: Int? = null,

    @field:SerializedName("subjectName")
    val subjectName: String? = null
) : Parcelable

@Parcelize
data class StudentsQuizzes(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("studentDegree")
    val studentDegree: Int? = null,

    @field:SerializedName("time")
    val time: Int? = null,

    @field:SerializedName("phoneNumber")
    val phoneNumber: String? = null,

    @field:SerializedName("quizId")
    val quizId: Int? = null,

    @field:SerializedName("quizName")
    val quizName: String? = null,

    @field:SerializedName("studentId")
    val studentId: Int? = null,

    @field:SerializedName("studentName")
    val studentName: String? = null,
) : Parcelable

@Parcelize
data class QuestionsItem(

    @field:SerializedName("wrongAnswers")
    val wrongAnswers: String? = null,

    @field:SerializedName("wrongAnswers2")
    val wrongAnswers2: String? = null,

    @field:SerializedName("wrongAnswers3")
    val wrongAnswers3: String? = null,

    @field:SerializedName("quizId")
    val quizId: Int? = null,

    @field:SerializedName("grade")
    val grade: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("correctAnwer")
    val correctAnwer: String? = null,

    val checkedPosition: Int=-1

) : Parcelable {

}