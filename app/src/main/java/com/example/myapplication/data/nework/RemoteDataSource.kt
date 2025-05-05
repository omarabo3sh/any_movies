package com.example.myapplication.data.nework

import com.example.myapplication.data.models.RemoteMovieModel
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) : IRemoteDataSource {


    override suspend fun getMovies():  Result<RemoteMovieModel> {
        return try {
            val data= apiService.getMovies()
             Result.success(data)
        } catch (e: Exception) {
             Result.failure(e)
        }
    }

    override suspend fun getMovieDetails(movieId: Int) =
        apiService.getMovieDetails(movieId)


}