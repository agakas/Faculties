package com.example.facultiesapp.repository


import com.example.facultiesapp.models.Faculty
import com.example.facultiesapp.network.NetworkProvider
import com.example.facultiesapp.database.DatabaseProvider
import com.example.facultiesapp.models.Department
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.util.*

class DepartmentRepository {
    private val myApi = NetworkProvider.get().myApi
    private val departmentDao = DatabaseProvider.get().getDepartmentDao()

    fun observeDepartments(departmentId: Int): Flow<List<Department>> {
        return departmentDao.getDepartments(departmentId)
    }

    suspend fun refreshDepartments() {
        val departments = myApi.getDepartments()
        departmentDao.insertAll(departments)
    }
    suspend fun add(department: Department) {
        val departmentFromServer = myApi.addDepartment(department)
        departmentDao.insert(departmentFromServer)
    }
    suspend fun update(department: Department) {
        val departmentFromServer =
            myApi.updateDepartment(department.id, department)
        departmentDao.insert(departmentFromServer)
    }
    suspend fun deleteById(id: Int) {
        myApi.deleteDepartment(id)
        departmentDao.deleteById(id)
    }
    suspend fun getDepartmentById(id: Int): Department {
        return departmentDao.getDepartmentById(id)
    }
    companion object {
        private var INSTANCE: DepartmentRepository? = null
        fun getInstance(): DepartmentRepository {
            if (INSTANCE == null) {
                INSTANCE = DepartmentRepository()
            }
            return INSTANCE ?: throw IllegalStateException("DepartmentRepository isn't init")
        }
    }
}