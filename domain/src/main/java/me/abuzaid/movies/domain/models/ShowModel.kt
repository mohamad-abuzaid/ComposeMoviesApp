package me.abuzaid.movies.domain.models

import kotlinx.serialization.SerialName

/**
 * Created by "Mohamad Abuzaid" on 08/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
data class ShowModel(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val firstAirDate: String,
    val name: String,
    val voteAverage: Double,
    val voteCount: Int
)