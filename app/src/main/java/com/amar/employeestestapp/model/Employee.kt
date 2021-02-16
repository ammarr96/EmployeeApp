package com.amar.employeestestapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amar.employeestestapp.util.Constants
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "employees")
class Employee(

        @PrimaryKey(autoGenerate = true)
        var id: Int,

        @NotNull
        @SerializedName("name")
        @ColumnInfo(name = "name",  defaultValue = "") var name: String,

        @NotNull
        @SerializedName("surname")
        @ColumnInfo(name = "surname",  defaultValue = "") var surname: String,

        @NotNull
        @SerializedName("title")
        @ColumnInfo(name = "title",  defaultValue = "") var title: String,

        @NotNull
        @SerializedName("department")
        @ColumnInfo(name = "department", defaultValue = "") var department: String,

        @NotNull
        @SerializedName("image")
        @ColumnInfo(name = "image",  defaultValue = "") var image: String,

        @NotNull
        @SerializedName("agency")
        @ColumnInfo(name = "agency",  defaultValue = "") var agency: String,

        @NotNull
        @SerializedName("intro")
        @ColumnInfo(name = "intro",  defaultValue = "") var intro: String,

        @NotNull
        @SerializedName("description")
        @ColumnInfo(name = "description",  defaultValue = "") var description: String,) : Serializable {

            fun getImageUrl() : String {
                return String.format("%s%s/%s.jpg", Constants.BASE_URL, "images/members", image)
            }

        }
