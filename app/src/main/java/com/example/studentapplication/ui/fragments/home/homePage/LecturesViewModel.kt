package com.example.studentapplication.ui.fragments.home.homePage

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentapplication.data.remote.response.get_lectures.GetLecturesResponseItem
import com.example.studentapplication.domin.repository.LecturesRepository
import com.example.studentapplication.utils.Network
import com.example.studentapplication.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LecturesViewModel @Inject constructor(
    private val lecturesRepository: LecturesRepository,
    private val app: Application
) : ViewModel() {

    private val _allLecturesLiveData = MutableLiveData<State<List<GetLecturesResponseItem>>>()
    val allLecturesLiveData: LiveData<State<List<GetLecturesResponseItem>>> = _allLecturesLiveData

    fun getAllLectures(token: String) {
        if (Network.isConnected()) {
            viewModelScope.launch {
                _allLecturesLiveData.postValue(State.Loading)
                val response = lecturesRepository.getAllLectures(token)
                if (response.isSuccessful) {
                    _allLecturesLiveData.postValue(State.Success(response.body()))
                } else {
                    when (response.code()) {
                        401 -> {
                            _allLecturesLiveData.postValue(State.Failure("انتهت صلاحية الدخول ! أعد الدخول مرة أخري "))
                        }

                        400 -> {
                            _allLecturesLiveData.postValue(State.Failure("حدث خطأ ما أعد المحاولة في وقت لاحق !"))
                        }
                        else ->
                            _allLecturesLiveData.postValue(State.Failure("حدث خطأ ما أعد المحاولة في وقت لاحق !"))

                    }
                }
            }
        } else {
            _allLecturesLiveData.postValue(State.NoInternet("لا يتوفر انترنت ! تأكد من اتصالك جيدا ثم أعد المحاولة !"))
        }


    }

}