package me.abuzaid.movies.models.mappers

import me.abuzaid.movies.domain.models.SearchModel
import me.abuzaid.movies.models.MovieDisplay
import me.abuzaid.movies.models.SearchDisplay
import me.abuzaid.movies.models.ShowDisplay

/**
 * Created by "Mohamad Abuzaid" on 08/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
fun SearchModel.toSearchDisplay() = SearchDisplay(
    id,
    mediaType,
    adult,
    backdropPath,
    genreIds,
    originalLanguage,
    originalTitle,
    overview,
    popularity,
    posterPath,
    releaseDate,
    firstAirDate,
    title,
    name,
    video,
    voteAverage,
    voteCount
)

fun List<SearchModel>.toSearchDisplayList() =
    map { it.toSearchDisplay() }

fun SearchDisplay.toMovieDisplay() = MovieDisplay(
    id,
    adult,
    backdropPath,
    genreIds,
    originalLanguage,
    originalTitle,
    overview,
    popularity,
    posterPath,
    releaseDate,
    title,
    video,
    voteAverage,
    voteCount
)

fun SearchDisplay.toShowDisplay() = ShowDisplay(
    id,
    adult,
    backdropPath,
    genreIds,
    listOf(),
    originalLanguage,
    originalTitle,
    overview,
    popularity,
    posterPath,
    firstAirDate,
    name,
    voteAverage,
    voteCount
)