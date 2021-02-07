package com.amar.employeestestapp.network

import com.amar.employeestestapp.model.Employee
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("teltechiansFlat.json")
    fun getEmployees(): Call<List<Employee>?>

}