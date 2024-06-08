package me.abuzaid.movies.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by "Mohamad Abuzaid" on 08/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
@Serializable
data class ShowRemote(
    @SerialName("id")
    val id: Int?,

    @SerialName("adult")
    val adult: Boolean?,

    @SerialName("backdrop_path")
    val backdropPath: String?,

    @SerialName("genre_ids")
    val genreIds: List<Int>?,

    @SerialName("origin_country")
    val originCountry: List<String>?,

    @SerialName("original_language")
    val originalLanguage: String?,

    @SerialName("original_name")
    val originalName: String?,

    @SerialName("overview")
    val overview: String?,

    @SerialName("popularity")
    val popularity: Double?,

    @SerialName("poster_path")
    val posterPath: String?,

    @SerialName("first_air_date")
    val firstAirDate: String?,

    @SerialName("name")
    val name: String?,

    @SerialName("vote_average")
    val voteAverage: Double?,

    @SerialName("vote_count")
    val voteCount: Int?
)