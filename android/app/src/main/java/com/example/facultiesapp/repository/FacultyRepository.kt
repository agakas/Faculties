package com.example.facultiesapp.repository

import com.example.facultiesapp.database.DatabaseProvider
import com.example.facultiesapp.models.Faculty
import com.example.facultiesapp.network.NetworkProvider
import kotlinx.coroutines.flow.Flow

class FacultyRepository {
    private val myApi = NetworkProvider.get().myApi
    private val facultyDao = DatabaseProvider.get().getFacultyDao()

    fun observeFaculties(): Flow<List<Faculty>> {
        return facultyDao.getFaculties()
    }
    suspend fun getFacultyById(id: Int): Faculty {
        return facultyDao.getFacultyById(id)
    }
    suspend fun refreshFaculties() {
        val faculties = myApi.getFaculties()
        facultyDao.insertAll(faculties)
    }
    suspend fun add(faculty: Faculty) {
        val facultyFromServer = myApi.addFaculty(faculty)
        facultyDao.insert(facultyFromServer)
    }
    suspend fun update(faculty: Faculty) {
        val facultyFromServer = myApi.updateFaculty(faculty.id, faculty)
        facultyDao.insert(facultyFromServer)
    }
    suspend fun delete(faculty: Faculty) {
        myApi.deleteFaculty(faculty.id)
        facultyDao.deleteFaculty(faculty)
    }
    companion object {
        private var INSTANCE: FacultyRepository? = null
        fun getInstance(): FacultyRepository {
            if (INSTANCE == null) {
                INSTANCE = FacultyRepository()
            }
            return INSTANCE ?: throw IllegalStateException("FacultyRepository isn't init")
        }
    }
}