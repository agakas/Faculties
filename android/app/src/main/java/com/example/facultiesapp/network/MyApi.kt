package com.example.facultiesapp.network

import com.example.facultiesapp.models.Department
import com.example.facultiesapp.models.Faculty
import retrofit2.Response
import retrofit2.http.*

interface MyApi {

    @GET("faculties/")
    suspend fun getFaculties(): List<Faculty>

    @POST("faculties/")
    suspend fun addFaculty(@Body faculty: Faculty): Faculty

    @PATCH("faculties/{id}/")
    suspend fun updateFaculty(@Path("id") id: Int, @Body faculty: Faculty): Faculty

    @DELETE("faculties/{id}/")
    suspend fun deleteFaculty(@Path("id") id: Int): Response<Unit>

    @GET("departments/")
    suspend fun getDepartments(): List<Department>

    @POST("departments/")
    suspend fun addDepartment(@Body department: Department): Department

    @PATCH("departments/{id}/")
    suspend fun updateDepartment(@Path("id") id: Int, @Body department: Department): Department

    @DELETE("departments/{id}/")
    suspend fun deleteDepartment(@Path("id") id: Int): Response<Unit>

}