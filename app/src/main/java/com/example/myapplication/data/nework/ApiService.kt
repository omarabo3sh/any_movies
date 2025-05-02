package com.example.myapplication.data.nework

import com.example.myapplication.data.MovieDetailsModel
import com.example.myapplication.data.MovieModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {


    @GET("discover/movie")
    suspend fun getMovies(): MovieModel // get all movies

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(@Path("movieId") movieId: Int): MovieDetailsModel // get movie details by id


}