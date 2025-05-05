package com.example.myapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.models.MovieResult

@Database(entities = [MovieResult::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MyDataBase : RoomDatabase() {

    abstract fun getDao(): MyDao // abstract function to get the DAO


}