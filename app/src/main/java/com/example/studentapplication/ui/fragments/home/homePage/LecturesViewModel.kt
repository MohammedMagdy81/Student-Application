package com.example.studentapplication.ui.fragments.home.homePage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentapplication.data.remote.response.get_lectures.GetLecturesResponseItem
import com.example.studentapplication.domin.repository.LecturesRepository
import com.example.studentapplication.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LecturesViewModel @Inject constructor(
    private val lecturesRepository: LecturesRepository
) : ViewModel() {

    private val _allLecturesLiveData = MutableLiveData<State<List<GetLecturesResponseItem>>>()
    val allLecturesLiveData: LiveData<State<List<GetLecturesResponseItem>>> = _allLecturesLiveData

    fun getAllLectures(token: String) {
        viewModelScope.launch {
            _allLecturesLiveData.value = lecturesRepository.getAllLectures(token)
        }
    }

}