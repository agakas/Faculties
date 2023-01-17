package com.example.facultiesapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Faculty.TABLE_NAME)
data class Faculty(
    val faculty_name: String,
    @PrimaryKey val id: Int
) {
    companion object {
        const val TABLE_NAME = "faculty"
    }
}