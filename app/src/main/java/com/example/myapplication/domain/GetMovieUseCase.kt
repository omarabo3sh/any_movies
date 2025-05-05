package com.example.myapplication.domain

import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: IRepository
) {
    suspend operator fun invoke(): Result<DomainMovieModel> {
        return repository.getMovies()
    }
}

