package com.example.myapplication.data.nework

import com.example.myapplication.data.models.RemoteMovieDetailsModel
import com.example.myapplication.data.models.RemoteMovieModel

interface IRemoteDataSource  {


    suspend fun getMovies(): Result<RemoteMovieModel> // get all movies

    suspend fun getMovieDetails(movieId: Int): RemoteMovieDetailsModel // get movie details by id


}