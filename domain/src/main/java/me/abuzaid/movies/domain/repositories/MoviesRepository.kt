package me.abuzaid.movies.domain.repositories

import kotlinx.coroutines.flow.Flow
import me.abuzaid.movies.domain.models.MovieModel
import me.abuzaid.movies.domain.models.wrappers.CallFailure
import me.abuzaid.movies.domain.models.wrappers.NetworkCallResult

/**
 * Created by "Mohamad Abuzaid" on 08/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
interface MoviesRepository {
    suspend fun fetchPopularMovies(lang: String): Flow<NetworkCallResult<List<MovieModel>, CallFailure>>
}