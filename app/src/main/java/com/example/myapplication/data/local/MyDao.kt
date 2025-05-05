package com.example.myapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.models.MovieResult

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // replace the old one if it exists
    suspend fun insertMovies(list: List<MovieResult>) // insert movies into the database

    @Query("select * from MovieResult")
    suspend fun getMovies(): List<MovieResult> // get all movies










}