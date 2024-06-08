package me.abuzaid.movies.models.mappers

import me.abuzaid.movies.domain.models.ShowModel
import me.abuzaid.movies.models.ShowDisplay

/**
 * Created by "Mohamad Abuzaid" on 08/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
fun ShowModel.toShowDisplay() = ShowDisplay(
    id,
    adult,
    backdropPath,
    genreIds,
    originCountry,
    originalLanguage,
    originalName,
    overview,
    popularity,
    posterPath,
    firstAirDate,
    name,
    voteAverage,
    voteCount
)

fun List<ShowModel>.toShowDisplayList() =
    map { it.toShowDisplay() }