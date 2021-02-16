package com.amar.employeestestapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amar.employeestestapp.model.Employee

@Dao
interface EmployeeDAO {

    @Query("SELECT * from employees")
    fun getAllEmployees(): LiveData<List<Employee>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllEmployees(list: List<Employee>)

    @Query("DELETE FROM employees")
    fun deleteAll()

}