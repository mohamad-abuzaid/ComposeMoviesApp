package me.abuzaid.movies.viewmodels

import androidx.paging.PagingData
import kotlinx.coroutines.flow.MutableStateFlow
import me.abuzaid.movies.models.MovieDisplay

/**
 * Created by "Mohamad Abuzaid" on 08/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
data class PopularState(
    val loading: Boolean = false,
    val success: List<MovieDisplay>? = null,
    val error: String? = null
)

data class ShowsState(
    val loading: Boolean = false,
    val success: MutableStateFlow<PagingData<MovieDisplay>>? = null,
    val error: String? = null
)