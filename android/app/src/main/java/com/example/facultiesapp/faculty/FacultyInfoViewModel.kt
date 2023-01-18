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

class FacultyInfoViewModel: ViewModel() {
    val facultyLiveData = MutableLiveData<Faculty?>()
    private val facultyRepository = FacultyRepository.getInstance()

    val defaultErrorHandler: CoroutineContext = CoroutineExceptionHandler { _, error ->
        Log.e(
            null,
            error.message,
            error
        )
    }
    fun getFaculty(id: Int) {
        viewModelScope.launch(defaultErrorHandler) {
            facultyLiveData.postValue(facultyRepository.getFacultyById(id))
        }
    }

    fun saveFaculty(newFaculty: Faculty) {
        viewModelScope.launch(defaultErrorHandler) {
            facultyLiveData.value?.let { oldFaculty ->
                facultyRepository.update(newFaculty.copy(id = oldFaculty.id))
            } ?: facultyRepository.add(newFaculty)
        }
    }
}