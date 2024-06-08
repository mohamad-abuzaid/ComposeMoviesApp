package me.abuzaid.movies.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 * Created by "Mohamad Abuzaid" on 08/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
@Serializable
@Parcelize
data class ShowDisplay(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    override val overview: String,
    val popularity: Double,
    override val posterPath: String,
    val firstAirDate: String,
    val name: String,
    override val voteAverage: Double,
    override val voteCount: Int
): Parcelable, MediaDisplay{
    override val header: String
        get() = name
    override val date: String
        get() = firstAirDate
}