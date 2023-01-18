package com.example.facultiesapp.department


import com.example.facultiesapp.InitParams
import kotlinx.parcelize.Parcelize

@Parcelize
data class Department_Info_Fragment_Init(
    val faculty: Int,
    val id: Int? = null
) : InitParams