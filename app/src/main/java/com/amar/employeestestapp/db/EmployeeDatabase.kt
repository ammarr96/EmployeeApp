package com.amar.employeestestapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amar.employeestestapp.model.Employee


@Database(entities = [Employee::class], version = 1)
abstract class EmployeeDatabase : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDAO

}