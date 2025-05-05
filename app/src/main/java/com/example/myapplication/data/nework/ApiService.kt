package com.example.myapplication.data.nework

import com.example.myapplication.data.models.RemoteMovieDetailsModel
import com.example.myapplication.data.models.RemoteMovieModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("discover/movie")
    suspend fun getMovies( ): RemoteMovieModel // get all movies

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(@Path("movieId") movieId: Int): RemoteMovieDetailsModel // get movie details by id


}