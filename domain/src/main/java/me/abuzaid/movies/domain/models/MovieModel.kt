package me.abuzaid.movies.domain.models

/**
 * Created by "Mohamad Abuzaid" on 26/05/2024.
 * Email: mabuzaid@sure.com.sa
 */
data class MovieModel(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)