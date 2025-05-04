package com.example.myapplication.data.nework

import com.example.myapplication.data.MovieDetailsModel
import com.example.myapplication.data.MovieModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("discover/movie")
    suspend fun getMovies( @Query("page") page: Int ): MovieModel // get all movies

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(@Path("movieId") movieId: Int): MovieDetailsModel // get movie details by id


}