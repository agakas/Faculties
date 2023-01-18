package com.example.facultiesapp.department

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.facultiesapp.models.Department
import com.example.facultiesapp.repository.DepartmentRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DepartmentInfoViewModel: ViewModel() {
    val departmentLiveData = MutableLiveData<Department?>()
    private val departmentRepository = DepartmentRepository.getInstance()

    val defaultErrorHandler: CoroutineContext = CoroutineExceptionHandler { _, error ->
        Log.e(
            null,
            error.message,
            error
        )
    }
    fun getDepartment(id: Int) {
        viewModelScope.launch(defaultErrorHandler) {
            departmentLiveData.postValue(departmentRepository.getDepartmentById(id))
        }
    }
    fun saveDepartment(newDepartment: Department) {
        viewModelScope.launch(defaultErrorHandler) {
            departmentLiveData.value?.let {oldDepartment ->
                departmentRepository.update(newDepartment.copy(id = oldDepartment.id))
            } ?: departmentRepository.add(newDepartment)
        }
    }
}