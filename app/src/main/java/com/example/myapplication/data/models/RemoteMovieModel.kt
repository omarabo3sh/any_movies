package com.example.myapplication.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class RemoteMovieModel(
    val page: Int?,
    val results: List<MovieResult>?,
    val total_pages: Int?,
    val total_results: Int?
)

@Entity
data class MovieResult(
    @PrimaryKey
    val id: Int?,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val vote_average: Double?,
    val vote_count: Int?,
    val original_title: String?,
    val popularity: Double?,
)