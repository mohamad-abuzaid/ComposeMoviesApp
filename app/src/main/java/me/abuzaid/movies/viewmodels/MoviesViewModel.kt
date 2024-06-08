package me.abuzaid.movies.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import me.abuzaid.movies.domain.usecases.MoviesUseCases
import me.abuzaid.movies.models.mappers.toMovieDisplay
import me.abuzaid.movies.models.mappers.toMovieDisplayList
import java.io.IOException

/**
 * Created by "Mohamad Abuzaid" on 08/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
class MoviesViewModel(
    application: Application,
    private val moviesUseCases: MoviesUseCases
) : AndroidViewModel(application) {

    var popularState by mutableStateOf(PopularState())
        private set

    var showsState by mutableStateOf(ShowsState())
        private set

    fun onEvent(event: MoviesEvents) {
        when (event) {
            is MoviesEvents.FetchPopular -> fetchPopularMovies(event.lang)
            is MoviesEvents.FetchShows -> fetchTvShows(event.lang)
        }
    }

    private var fetchPopularJob: Job? = null
    private fun fetchPopularMovies(lang: String) {
        fetchPopularJob = viewModelScope.launch {
            popularState = popularState.copy(loading = true)
            try {
                moviesUseCases.fetchPopularMovies(lang).collect { result ->
                    popularState = if (result.error == null) {
                        popularState.copy(
                            success = result.value?.toMovieDisplayList(),
                            loading = false
                        )
                    } else {
                        popularState.copy(
                            error = result.error?.errorModel?.errorMessage,
                            loading = false
                        )
                    }
                }
            } catch (ioe: IOException) {
                popularState = popularState.copy(
                    error = "Unknown Error",
                    loading = false
                )
            }
        }
    }

    private var fetchShowsJob: Job? = null
    private fun fetchTvShows(lang: String) {
        fetchShowsJob = viewModelScope.launch {
            showsState = showsState.copy(loading = true)
            try {
                moviesUseCases.fetchTvShows(lang)
                    .distinctUntilChanged()
                    .cachedIn(viewModelScope)
                    .collect { result ->
                        showsState = showsState.copy(
                            loading = false,
                            success = MutableStateFlow(result.map { list -> list.toMovieDisplay() })
                        )
                    }

            } catch (ioe: IOException) {
                showsState = showsState.copy(
                    loading = false,
                    error = "Unknown Error",
                )
            }
        }
    }
}