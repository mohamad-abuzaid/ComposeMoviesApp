package me.abuzaid.movies.data.network.models.mappers

import me.abuzaid.movies.data.network.models.ShowRemote
import me.abuzaid.movies.domain.models.ShowModel

/**
 * Created by "Mohamad Abuzaid" on 08/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
fun ShowRemote.toShowModel() = ShowModel(
    id ?: -1,
    adult ?: false,
    backdropPath ?: "",
    genreIds ?: listOf(),
    originCountry ?: listOf(),
    originalLanguage ?: "",
    originalName ?: "",
    overview ?: "",
    popularity ?: 0.0,
    posterPath ?: "",
    firstAirDate ?: "",
    name ?: "",
    voteAverage ?: 0.0,
    voteCount ?: -1
)

fun List<ShowRemote>.toShowModelList() =
    map { it.toShowModel() }