package com.example.studentapplication.domin.model

import com.google.gson.annotations.SerializedName

data class StudentQuizBody(

	@field:SerializedName("studentId")
	val studentId: Int? = null,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String? = null,

	@field:SerializedName("quizId")
	val quizId: Int? = null,

	@field:SerializedName("quizName")
	val quizName: String? = null,

	@field:SerializedName("studentName")
	val studentName: String? = null,

	@field:SerializedName("studentDegree")
	val studentDegree: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("time")
	val time: Int? = null
)
