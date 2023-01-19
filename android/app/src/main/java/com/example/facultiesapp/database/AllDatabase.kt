package com.example.facultiesapp.database

import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.facultiesapp.models.Department
import com.example.facultiesapp.models.Faculty

@Database(
    entities =
    [
        Faculty::class,
        Department::class,
    ],
    version = 4
)
//@TypeConverters(Converters::class)
abstract class AllDatabase: RoomDatabase(){
    abstract fun facultyDao(): FacultyDao
    abstract fun departmentDao(): DepartmentDao

    companion object {
        const val DB_NAME = "faculties_app_database"
    }
}