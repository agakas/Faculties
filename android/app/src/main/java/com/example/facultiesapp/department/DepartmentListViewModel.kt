package com.example.facultiesapp.department

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.facultiesapp.models.Department
import com.example.facultiesapp.repository.DepartmentRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*
import kotlin.coroutines.CoroutineContext

class DepartmentListViewModel: ViewModel() {
    val departmentsLiveData = MutableLiveData<List<Department>>()
    private val departmentRepository = DepartmentRepository.getInstance()

    val defaultErrorHandler: CoroutineContext = CoroutineExceptionHandler { _, error ->
        Log.e(
            null,
            error.message,
            error
        )
    }
    fun getDepartments(facultyId: Int) {
        viewModelScope.launch(defaultErrorHandler) {
            departmentRepository.observeDepartments(facultyId).collect {
                departmentsLiveData.postValue(it)
            }
        }
        viewModelScope.launch(defaultErrorHandler) {
            departmentRepository.refreshDepartments()
        }
    }

    fun onDeleteClick(departmentShort: Department) {
        viewModelScope.launch(defaultErrorHandler) {
            departmentRepository.deleteById(departmentShort.id)
        }
    }
}