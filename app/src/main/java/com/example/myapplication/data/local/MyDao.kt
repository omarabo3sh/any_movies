package com.example.myapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.MovieResult

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // replace the old one if it exists
    suspend fun insertMovies(list: List<MovieResult>) // insert movies into the database

    @Query("select * from MovieResult")
    suspend fun getMovies(): List<MovieResult> // get all movies

//    @Query("select * from MovieResult where id = :id")
//    suspend fun getMovieById(id: Int): MovieResult?  wrong cause its for cashing not calling









}