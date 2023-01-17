package com.example.facultiesapp.database

import kotlinx.coroutines.flow.Flow
import androidx.room.*
import com.example.facultiesapp.models.Faculty
import java.time.LocalDate
import java.util.*

interface FacultyDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getFaculties(): Flow<List<Faculty>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = (:id)")
    suspend fun getFacultyById(id: Int): Faculty

    @Update
    suspend fun updateFaculty(faculty: Faculty)

    @Query("DELETE FROM $TABLE_NAME WHERE  id = (:Id)")
    suspend fun deleteById(id: Int)

    @Delete
    suspend fun deleteFaculty(faculty: Faculty)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(faculty: Faculty)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Faculty>)

    companion object {
        private const val TABLE_NAME = Faculty.TABLE_NAME
    }
}