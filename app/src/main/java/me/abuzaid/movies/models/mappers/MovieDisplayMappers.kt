package me.abuzaid.movies.models.mappers

import me.abuzaid.movies.domain.models.MovieModel
import me.abuzaid.movies.models.MovieDisplay

/**
 * Created by "Mohamad Abuzaid" on 08/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
fun MovieModel.toMovieDisplay() = MovieDisplay(
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

fun List<MovieModel>.toMovieDisplayList() =
    map { it.toMovieDisplay() }