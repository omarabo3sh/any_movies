package com.example.myapplication.mvvm

import com.example.myapplication.data.MovieDetailsModel
import com.example.myapplication.data.MovieResult
import com.example.myapplication.data.local.MyDao
import com.example.myapplication.data.nework.ApiService
import jakarta.inject.Inject

class Repository @Inject constructor(val apiService: ApiService, val dao: MyDao) {

    suspend fun getMovies(): List<MovieResult>? { // this is the main function that
        // gets the data from the api and inserts it into the local database

        val data = getMovieRemote()
        return data
    }

    private suspend fun getMovieRemote(): List<MovieResult>? { // this gets the data from the api

        return try {
            val data = apiService.getMovies()
            insert(data.results) // this inserts the data into the local database
            data.results // this returns the data to the main function
        } catch (_: Exception) {
            getMovieFromCache() // this gets the data from the local database
        }
    }


    private suspend fun insert(movieResult: List<MovieResult>?) { // this inserts the data into the local database
        movieResult?.let {
            dao.insertMovies(it) // this inserts the data into the local database
        }

    }

    private suspend fun getMovieFromCache(): List<MovieResult> { // this gets the data from the local database
        return dao.getMovies()

    }


    suspend fun getMovieDetails(id: Int): MovieDetailsModel { // this gets the data from the api
        return apiService.getMovieDetails(id)
    }


}


