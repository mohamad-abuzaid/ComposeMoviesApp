package me.abuzaid.movies.data.repositories

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.abuzaid.movies.data.network.models.mappers.MapThrowableToCallFailure
import me.abuzaid.movies.data.network.models.mappers.UnKnownError
import me.abuzaid.movies.data.network.models.mappers.toMovieModelList
import me.abuzaid.movies.data.network.services.ApiServices
import me.abuzaid.movies.domain.models.MovieModel
import me.abuzaid.movies.domain.models.wrappers.CallFailure
import me.abuzaid.movies.domain.models.wrappers.NetworkCallResult
import me.abuzaid.movies.domain.repositories.MoviesRepository

/**
 * Created by "Mohamad Abuzaid" on 08/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
class MoviesRepositoryImpl(
    private val moviesApiServices: ApiServices,
    private val dispatcher: CoroutineDispatcher
) : MoviesRepository {
    override suspend fun fetchPopularMovies(lang: String) =
        flow<NetworkCallResult<List<MovieModel>, CallFailure>> {
            val result = moviesApiServices.fetchPopularMovies(lang)

            result.results?.let {
                emit(NetworkCallResult(value = it.toMovieModelList()))
            } ?: run {
                emit(NetworkCallResult(error = UnKnownError()))
            }

        }.flowOn(dispatcher)
            .catch { e ->
                emit(NetworkCallResult(error = MapThrowableToCallFailure.map(e)))
            }
}