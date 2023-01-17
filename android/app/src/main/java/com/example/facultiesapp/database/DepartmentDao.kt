package com.example.facultiesapp.database

import kotlinx.coroutines.flow.Flow
import androidx.room.*
import com.example.facultiesapp.models.Department
import java.time.LocalDate
import java.util.*

@Dao
interface DepartmentDao {

    @Query("SELECT * FROM $TABLE_NAME WHERE faculty = (:faculty)")
    fun getDepartments(faculty: Int): Flow<List<Department>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = (:id)")
    suspend fun getDepartmentById(id: Int): Department

    @Update
    suspend fun update(department: Department)

    @Update
    suspend fun updateAll(list: List<Department>)

    @Delete
    suspend fun delete(tour: Department)

    @Query("DELETE FROM $TABLE_NAME WHERE  id = (:Id)")
    suspend fun deleteById(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(department: Department)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Department>)

    companion object {
        private const val TABLE_NAME = Department.TABLE_NAME
    }
}