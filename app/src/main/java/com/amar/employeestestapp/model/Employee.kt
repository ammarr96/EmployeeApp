package com.amar.employeestestapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amar.employeestestapp.Constants
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "employees")
class Employee(

        @PrimaryKey(autoGenerate = true)
        var id: Int,

        @SerializedName("name")
        @ColumnInfo(name = "name") var name: String,

        @SerializedName("surname")
        @ColumnInfo(name = "surname") var surname: String,

        @SerializedName("title")
        @ColumnInfo(name = "title") var title: String,

        @SerializedName("department")
        @ColumnInfo(name = "department") var department: String,

        @SerializedName("image")
        @ColumnInfo(name = "image") var image: String,

        @SerializedName("agency")
        @ColumnInfo(name = "agency") var agency: String,

        @SerializedName("intro")
        @ColumnInfo(name = "intro") var intro: String,

        @SerializedName("description")
        @ColumnInfo(name = "description") var description: String,) : Serializable {

            fun getImageUrl() : String {
                return String.format("%s%s/%s.jpg", Constants.BASE_URL, "images/members", image)
            }

        }
