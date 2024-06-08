package me.abuzaid.movies.data.network.models.mappers

import me.abuzaid.movies.data.network.models.SearchRemote
import me.abuzaid.movies.domain.models.SearchModel

/**
 * Created by "Mohamad Abuzaid" on 08/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
fun SearchRemote.toSearchModel() = SearchModel(
    id ?: -1,
    mediaType ?: "",
    adult ?: false,
    backdropPath ?: "",
    genreIds ?: listOf(),
    originalLanguage ?: "",
    originalTitle ?: "",
    overview ?: "",
    popularity ?: 0.0,
    posterPath ?: "",
    releaseDate ?: "",
    firstAirDate ?: "",
    title ?: "",
    name ?: "",
    video ?: false,
    voteAverage ?: 0.0,
    voteCount ?: -1
)

fun List<SearchRemote>.toSearchModelList() =
    map { it.toSearchModel() }