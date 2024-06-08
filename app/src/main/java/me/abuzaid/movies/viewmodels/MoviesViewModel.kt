package me.abuzaid.movies.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.abuzaid.movies.domain.usecases.MoviesUseCases
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

    fun onEvent(event: MoviesEvents) {
        when (event) {
            is MoviesEvents.FetchPopular -> fetchPopularMovies(event.lang)
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
}