package com.example.facultiesapp

import androidx.fragment.app.Fragment
import com.example.facultiesapp.models.Faculty

interface Navigator {
    fun navigateToDepartment(faculty: Faculty)
    fun navigateToDepartmentCreateANDEdit(facultyId: Int, departmentId: Int? = null)
    fun navigateToFacultyCreateANDEdit(id: Int? = null)
    fun exit(fragment: Fragment)
}