package com.example.myapplication.data

import com.example.myapplication.data.models.MovieResult
import com.example.myapplication.data.models.RemoteMovieModel
import com.example.myapplication.domain.DomainMovieResult
import com.example.myapplication.domain.DomainMovieModel

//
//fun RemoteMovieModel.mapToDomain(): DomainMovieModel {
//    return DomainMovieModel(page, results?.map { it.mapToDomain() }, total_pages, total_results)
//}
//
//fun MovieResult.mapToDomain(): DomainMovieResult {
//    return DomainMovieResult(
//        adult,
//        backdrop_path,
//        genre_ids,
//        id,
//        original_language,
//        original_title,
//        overview,
//        popularity,
//        poster_path,
//        release_date,
//        title,
//        video,
//        vote_average,
//        vote_count
//    )


//}
// RemoteMovieModel -> DomainMovieModel
fun RemoteMovieModel.mapToDomain(): DomainMovieModel {
    return DomainMovieModel(
        page = page,
        results = results?.map { it.mapToDomain() },
        total_pages = total_pages,
        total_results = total_results
    )
}

// MovieResult -> DomainMovieResult
fun MovieResult.mapToDomain(): DomainMovieResult {
    return DomainMovieResult(
        id = id,
        original_title = original_title,
        overview = overview,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        vote_average = vote_average,
        vote_count = vote_count,
        popularity = popularity
    )
}

fun mapToPresentation(domainResult: DomainMovieResult): MovieResult {
    return MovieResult(
        id = domainResult.id,
        original_title = domainResult.title,
        poster_path = domainResult.poster_path,
        overview = domainResult.overview,
        release_date = domainResult.release_date,
        vote_average = domainResult.vote_average,
        title = domainResult.title,
        vote_count = domainResult.vote_count,
        popularity = domainResult.popularity
    )
}

