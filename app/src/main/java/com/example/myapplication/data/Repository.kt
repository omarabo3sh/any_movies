package com.example.myapplication.data

import com.example.myapplication.data.local.ILocalDataSource
import com.example.myapplication.data.nework.IRemoteDataSource
import com.example.myapplication.domain.IRepository
import com.example.myapplication.domain.DomainMovieModel
import javax.inject.Inject

class Repository @Inject constructor(
    private val iLocalDataSource: ILocalDataSource,
    private val iRemoteDataSource: IRemoteDataSource
) : IRepository {


    override suspend fun getMovies(): Result<DomainMovieModel> {
        val data = iRemoteDataSource.getMovies()

        return if (data.isSuccess) {
            Result.success(data.getOrNull()?.mapToDomain()!!)

        } else {
            Result.failure(data.exceptionOrNull()!!)
        }
    }


}