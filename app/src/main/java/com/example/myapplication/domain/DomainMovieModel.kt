package com.example.myapplication.domain


data class DomainMovieModel(
    val page: Int?,
    val results: List<DomainMovieResult>?,
    val total_pages: Int?,
    val total_results: Int?
)

data class DomainMovieResult(


    val id: Int?,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val vote_average: Double?,
    val vote_count: Int?,
    val original_title: String?
    , val popularity: Double?
)