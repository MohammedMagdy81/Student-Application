package com.example.studentapplication.data.remote.response.quizzes

import com.google.gson.annotations.SerializedName

data class AddStudentQuizResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null
)
