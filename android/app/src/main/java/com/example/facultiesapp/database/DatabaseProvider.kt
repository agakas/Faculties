package com.example.facultiesapp.database

import android.content.Context
import androidx.room.Room

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

    fun getFacultyDao(): FacultyDao = database.facultyDao()

    fun getDepartmentDao(): DepartmentDao = database.departmentDao()

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