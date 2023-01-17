package com.example.facultiesmapp.database

import android.content.Context
import androidx.room.Room
import com.example.facultiesapp.database.AllDatabase
import com.example.facultiesapp.database.DepartmentDao
import com.example.facultiesapp.database.FacultyDao

class DatabaseProvider(context: Context) {

    private val database: AllDatabase

    init {
        database = Room.databaseBuilder(
            context.applicationContext,
            AllDatabase::class.java,
            AllDatabase.DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    fun getTourfirmDao(): FacultyDao = database.facultyDao()

    fun getTourDao(): DepartmentDao = database.departmentDao()

    companion object {
        private var INSTANCE: DatabaseProvider? = null
        fun init(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = DatabaseProvider(context)
            }
        }
        fun get(): DatabaseProvider {
            return INSTANCE!!
        }
    }
}