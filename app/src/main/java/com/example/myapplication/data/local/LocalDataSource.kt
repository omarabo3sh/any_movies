package com.example.myapplication.data.local

import com.example.myapplication.data.models.MovieResult
import com.example.myapplication.data.models.RemoteMovieDetailsModel
import com.example.myapplication.data.models.RemoteMovieModel
import javax.inject.Inject

class  LocalDataSource @Inject constructor(private val movieDao: MyDao) : ILocalDataSource {

    override suspend fun getMovies(): List<MovieResult> {
        return movieDao.getMovies()
    }



    override suspend fun saveMovies(movies: List<MovieResult>) {
        movieDao.insertMovies(movies)
    }

//    override suspend fun getMovieDetails(movieId: Int): MovieDetailsModel {
//        return movieDao.getMovieDetails(movieId)
//    }
//    override suspend fun saveMovieDetails(movieDetails: MovieDetailsModel) {
//        movieDao.insertMovieDetails(movieDetails)
//    }
}