package com.amar.employeestestapp.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amar.employeestestapp.application.MyApplication
import com.amar.employeestestapp.model.Employee
import com.amar.employeestestapp.network.ApiService
import com.amar.employeestestapp.network.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EmployeesRepository {

    lateinit var employeeList : MutableLiveData<List<Employee>>
    private val apiService: ApiService

    init {
        apiService = RetrofitFactory().makeApiService()!!
    }

    fun getEmployees() : LiveData<List<Employee>>
    {
        return MyApplication.database!!.employeeDao().getAllEmployees()

    }

    fun getEmployeesFromApi()  {

        employeeList = MutableLiveData()

        val call = apiService.getEmployees()

        call.enqueue(object : Callback<List<Employee>?> {
            override fun onResponse(call: Call<List<Employee>?>, response: Response<List<Employee>?>) {

                when(response.code())
                {
                    200 ->{
                        Thread(Runnable {

                            MyApplication.database!!.employeeDao().deleteAll()
                            MyApplication.database!!.employeeDao().insertAllEmployees(response.body()!!)

                        }).start()
                    }
                }

            }

            override fun onFailure(call: Call<List<Employee>?>, t: Throwable) {

            }

        })

    }


}
