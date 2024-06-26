package me.abuzaid.movies.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.abuzaid.movies.domain.repositories.MoviesRepository

/**
 * Created by "Mohamad Abuzaid" on 08/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
class MoviesUseCases(
    private val moviesRepository: MoviesRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun fetchPopularMovies(lang: String) =
        withContext(dispatcher) {
            moviesRepository.fetchPopularMovies(lang)
        }

    suspend fun fetchMovies(lang: String) =
        withContext(dispatcher) {
            moviesRepository.fetchMovies(lang)
        }

    suspend fun fetchTvShows(lang: String) =
        withContext(dispatcher) {
            moviesRepository.fetchTvShows(lang)
        }

    suspend fun search(query: String, lang: String) =
        withContext(dispatcher) {
            moviesRepository.search(query, lang)
        }
}