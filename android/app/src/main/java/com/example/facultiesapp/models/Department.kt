package com.example.facultiesapp.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = Department.TABLE_NAME,
    foreignKeys =
    [ForeignKey(
        entity = Faculty::class,
        parentColumns = ["id"],
        childColumns = ["faculty"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.NO_ACTION
    )]
)
data class Department(
    @PrimaryKey var id: Int = 0,
    var department_name: String = "",
    var year: Int = 1900,
    var auditorium: String = "",
    var boss: String = "",
    var phone: String = "",
    var email: String = "",
    var employee_count: Int = 0,
    var bachelors_count: Int = 0,
    var master_count: Int = 0,
    var faculty: Int = 0,
) {
    companion object {
        const val TABLE_NAME = "department"
    }

}
