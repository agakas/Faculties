package com.example.facultiesapp.department

import com.example.facultiesapp.InitParams
import kotlinx.parcelize.Parcelize

@Parcelize
data class Department_List_Fragment_Init(
    val facultyId: Int,
    val facultyName: String
) : InitParams