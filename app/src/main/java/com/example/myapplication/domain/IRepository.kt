package com.example.myapplication.domain


interface IRepository {

    suspend fun getMovies(): Result<DomainMovieModel> // get all movies from the repository
}