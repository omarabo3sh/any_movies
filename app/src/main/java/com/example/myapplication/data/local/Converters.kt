package com.example.myapplication.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromCustomObjectList(value: List<Int>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toCustomObjectList(value: String?): List<Int>? {
        val listType = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(value, listType)
    }
}
