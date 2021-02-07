package com.amar.employeestestapp

import android.app.Application
import androidx.room.Room
import com.amar.employeestestapp.db.EmployeeDatabase

class MyApplication: Application() {

    companion object {
        var database: EmployeeDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        database =  Room.databaseBuilder(applicationContext, EmployeeDatabase::class.java, "employee_db").fallbackToDestructiveMigration().build()
    }

}