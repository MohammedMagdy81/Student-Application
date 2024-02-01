package com.example.studentapplication.ui.fragments.home.quizQuestions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentapplication.data.remote.response.quizzes.AddStudentQuizResponse
import com.example.studentapplication.domin.model.StudentQuizBody
import com.example.studentapplication.domin.repository.QuizRepository
import com.example.studentapplication.utils.Network
import com.example.studentapplication.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddStudentQuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository
) : ViewModel() {

    private val _addStudentQuiz = MutableLiveData<State<AddStudentQuizResponse>>()
    val addStudentQuiz: LiveData<State<AddStudentQuizResponse>> = _addStudentQuiz

    fun addStudentQuiz(studentQuizBody: StudentQuizBody) {
        viewModelScope.launch {
            if (Network.isConnected()) {
                _addStudentQuiz.postValue(State.Loading)
                val result = quizRepository.addStudentQuiz(studentQuizBody)
                if (result.isSuccessful) {
                    _addStudentQuiz.postValue(State.Success(result.body()))
                } else {
                    when (result.code()) {
                        403 -> {
                            _addStudentQuiz.postValue(State.Failure("انتهت  مدة الجلسة أعد تسجيل الدول مرة أخري !"))
                        }

                        else -> {
                            _addStudentQuiz.postValue(State.Failure(result.message()))

                        }
                    }
                }

            } else {
                _addStudentQuiz.postValue(State.NoInternet("تأكد جيدا من الاتصال بالانترنت حتي يتم تسجيل اجابتك !"))
            }

        }
    }
}









