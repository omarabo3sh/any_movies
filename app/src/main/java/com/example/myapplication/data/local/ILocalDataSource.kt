package com.example.myapplication.data.local

import com.example.myapplication.data.models.MovieResult
import com.example.myapplication.data.models.RemoteMovieDetailsModel
import com.example.myapplication.data.models.RemoteMovieModel

interface ILocalDataSource {

    suspend fun getMovies(): List<MovieResult> // get all movies


    suspend fun saveMovies(movies: List<MovieResult>) // save movies to local database

//    suspend fun getMovieDetails(movieId: Int): MovieDetailsModel // get movie details by id
//
//    suspend fun saveMovieDetails(movieDetails: MovieDetailsModel) // save movie details to local database


}