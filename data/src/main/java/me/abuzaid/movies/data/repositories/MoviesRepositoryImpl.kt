package me.abuzaid.movies.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.abuzaid.movies.data.network.models.mappers.toMovieModelList
import me.abuzaid.movies.data.network.services.ApiServices
import me.abuzaid.movies.data.pagination.MoviesPagingSource
import me.abuzaid.movies.data.pagination.SearchPagingSource
import me.abuzaid.movies.data.pagination.ShowsPagingSource
import me.abuzaid.movies.domain.models.MovieModel
import me.abuzaid.movies.domain.models.SearchModel
import me.abuzaid.movies.domain.models.ShowModel
import me.abuzaid.movies.domain.models.wrappers.CallFailure
import me.abuzaid.movies.domain.models.wrappers.ErrorModel
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
                emit(
                    NetworkCallResult(
                        error = CallFailure(
                            ErrorModel(
                                code = result.statusCode ?: -1,
                                errorMessage = result.statusMessage ?: ""
                            )
                        )
                    )
                )
            }

        }.flowOn(dispatcher)
            .catch { e ->
                emit(
                    NetworkCallResult(
                        error = CallFailure(
                            ErrorModel(
                                code = -1,
                                errorMessage = e.message ?: ""
                            )
                        )
                    )
                )
            }

    override suspend fun fetchMovies(lang: String): Flow<PagingData<MovieModel>> = Pager(
        config = PagingConfig(
            pageSize = 15,
            prefetchDistance = 2
        ),
        pagingSourceFactory = {
            MoviesPagingSource(
                apiServices = moviesApiServices,
                lang = lang
            )
        }
    ).flow.flowOn(dispatcher)

    override suspend fun fetchTvShows(lang: String): Flow<PagingData<ShowModel>> = Pager(
        config = PagingConfig(
            pageSize = 15,
            prefetchDistance = 2
        ),
        pagingSourceFactory = {
            ShowsPagingSource(
                apiServices = moviesApiServices,
                lang = lang
            )
        }
    ).flow.flowOn(dispatcher)

    override suspend fun search(query: String, lang: String): Flow<PagingData<SearchModel>> = Pager(
        config = PagingConfig(
            pageSize = 15,
            prefetchDistance = 2
        ),
        pagingSourceFactory = {
            SearchPagingSource(
                apiServices = moviesApiServices,
                query = query,
                lang = lang
            )
        }
    ).flow.flowOn(dispatcher)
}