package com.example.myapplication.domain

import javax.inject.Inject


class GetMovieSortedUseCase @Inject constructor() {
    fun getMovies(list: List<DomainMovieResult>): List<DomainMovieResult> {
        return list.sortedBy { it.popularity }
    }
}


