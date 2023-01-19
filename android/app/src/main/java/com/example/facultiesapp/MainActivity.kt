package com.example.facultiesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.facultiesapp.models.Faculty
import com.example.facultiesapp.department.DepartmentInfoFragment
import com.example.facultiesapp.department.DepartmentListFragment
import com.example.facultiesapp.department.Department_Info_Fragment_Init
import com.example.facultiesapp.department.Department_List_Fragment_Init
import com.example.facultiesapp.faculty.FacultyInfoFragment
import com.example.facultiesapp.faculty.FacultyListFragment
import com.example.facultiesapp.faculty.Faculty_Info_Fragment_Init

class MainActivity : AppCompatActivity(), Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(FacultyListFragment.newInstance())

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }
    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, fragment)
            .commit()
    }
    fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun removeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .remove(fragment)
            .commit()
        if (supportFragmentManager.fragments.isEmpty()) {
            finish()
        }
    }
    override fun navigateToFaculty(faculty: Faculty) {
        addFragment(
            DepartmentListFragment.newInstance(
                Department_List_Fragment_Init(faculty.id, faculty.faculty_name)
            )
        )
    }

    override fun navigateToDepartmentCreateANDEdit(facultyId: Int, departmentId: Int?) {
        addFragment(
            DepartmentInfoFragment.newInstance(Department_Info_Fragment_Init(facultyId,departmentId))
        )
    }

    override fun navigateToFacultyCreateANDEdit(id: Int?) {
        addFragment(FacultyInfoFragment.newInstance(Faculty_Info_Fragment_Init(id)))
    }

    override fun exit(fragment: Fragment) {
        removeFragment(fragment)
    }
}