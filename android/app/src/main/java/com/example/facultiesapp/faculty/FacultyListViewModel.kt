package com.example.facultiesapp.faculty

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.facultiesapp.models.Faculty
import com.example.facultiesapp.repository.FacultyRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FacultyListViewModel: ViewModel(){
    val facultiesLiveData = MutableLiveData<List<Faculty>>()
    private val facultyRepository = FacultyRepository.getInstance()

    val defaultErrorHandler: CoroutineContext = CoroutineExceptionHandler { _, error ->
        Log.e(
            null,
            error.message,
            error
        )
    }
    fun getFaculties() {
        viewModelScope.launch(defaultErrorHandler) {
            facultyRepository.observeFaculties().collect {
                facultiesLiveData.postValue(it)
            }
        }
        viewModelScope.launch(defaultErrorHandler) { facultyRepository.refreshFaculties() }
    }

    fun onDeleteFacultyClick(faculty: Faculty) {
        viewModelScope.launch(defaultErrorHandler) {
            facultyRepository.delete(faculty)
        }
    }
}