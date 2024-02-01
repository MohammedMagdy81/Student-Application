package com.example.studentapplication.ui.fragments.home.quiz

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentapplication.data.remote.response.quizzes.GetAllQuizResponse
import com.example.studentapplication.domin.repository.QuizRepository
import com.example.studentapplication.utils.Network
import com.example.studentapplication.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository,
    private val app: Application
) : ViewModel() {

    private val _allQuizzes = MutableLiveData<State<List<GetAllQuizResponse>>>()
    val allQuizzes: LiveData<State<List<GetAllQuizResponse>>> = _allQuizzes

    fun getAllQuizzes() {
        if (Network.isConnected()) {
            _allQuizzes.postValue(State.Loading)
            viewModelScope.launch {
                val result = quizRepository.getAllQuiz()
                if (result.isSuccessful) {
                    _allQuizzes.postValue(State.Success(result.body()))
                } else {
                    when (result.code()) {
                        403 -> {
                            _allQuizzes.postValue(State.Failure("انتهت صلاحية الدخول أعد تسجيل الدخول مرة أخري"))
                        }

                        500 -> {
                            _allQuizzes.postValue(State.Failure("هناك مشكلة في السيرفر "))
                        }
                        else -> {
                            _allQuizzes.postValue(State.Failure("حدث خطأ غير متوقع حاول مرة أخري "))
                        }
                    }
                }

            }


        } else {
            _allQuizzes.postValue(State.NoInternet("لا يتوفر انترنت  تأكد من اتصالك بالشبكة ثم أعد المحاولة!"))
        }
    }

    // 22891559-0efa-4002-b5fd-40e6d96c84ea Screenshot (5).png
   //  22891559-0efa-4002-b5fd-40e6d96c84ea Screenshot (5).png
   //  22891559-0efa-4002-b5fd-40e6d96c84ea Screenshot (5).png
}

//5f48dbcd-b912-40b6-ab02-e9860894d283 Screenshot (2).png
//C:\Users\abc\Desktop\Sigma\Sigma.APIs\wwwroot\FilesUplode\5f48dbcd-b912-40b6-ab02-e9860894d283 Screenshot (2).png











