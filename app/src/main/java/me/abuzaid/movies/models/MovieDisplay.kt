package me.abuzaid.movies.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 * Created by "Mohamad Abuzaid" on 05/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
@Serializable
@Parcelize
data class MovieDisplay(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val originalLanguage: String,
    val originalTitle: String,
    override val overview: String,
    val popularity: Double,
    override val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    override val voteAverage: Double,
    override val voteCount: Int
) : Parcelable, MediaDisplay{
    override val header: String
        get() = title
    override val date: String
        get() = releaseDate
}