package me.abuzaid.movies.domain.models

import kotlinx.serialization.Serializable

/**
 * Created by "Mohamad Abuzaid" on 08/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
@Serializable
data class SearchModel(
    val id: Int,
    val mediaType: String,
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val firstAirDate: String,
    val title: String,
    val name: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)