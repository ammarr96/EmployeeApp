package com.amar.employeestestapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.amar.employeestestapp.model.Employee
import com.amar.employeestestapp.db.EmployeesRepository

class EmployeeListViewModel: ViewModel() {

    val employeeRepository: EmployeesRepository

    val  employeeLiveData: LiveData<List<Employee>>

    init {

        employeeRepository = EmployeesRepository()
        this.employeeLiveData = employeeRepository.getEmployees()

    }

    fun getEmployeeData(): LiveData<List<Employee>>
    {
        return employeeRepository.getEmployees()
    }


    fun getEmployeesFromAPIAndStore() {
        employeeRepository.getEmployeesFromApi()
    }

}