package me.abuzaid.movies.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import me.abuzaid.movies.models.mappers.toMovieDisplay
import me.abuzaid.movies.models.mappers.toShowDisplay
import me.abuzaid.movies.navigation.MainScreens
import me.abuzaid.movies.ui.composables.MediaItem
import me.abuzaid.movies.ui.composables.loaders.ErrorView
import me.abuzaid.movies.ui.composables.loaders.LoadingIndicatorRotating
import me.abuzaid.movies.ui.composables.loaders.NoContentView
import me.abuzaid.movies.ui.composables.pages.ScreenPage
import me.abuzaid.movies.utils.LocalLang
import me.abuzaid.movies.utils.localization.LocalizationHelper
import me.abuzaid.movies.viewmodels.MoviesEvents
import me.abuzaid.movies.viewmodels.SearchState
import java.net.URLEncoder

/**
 * Created by "Mohamad Abuzaid" on 08/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
@Composable
fun SearchScreen(
    navController: NavController,
    state: SearchState,
    fireEvent: (MoviesEvents) -> Unit,
    query: String
) {
    val lang = LocalLang.current

    val searchPagedItems = state.success?.collectAsLazyPagingItems()

    /*****************************/

    LaunchedEffect(key1 = state) {
        if (state.success == null) {
            fireEvent(
                MoviesEvents.Search(query, LocalizationHelper.fullLocal(lang))
            )
        }
    }
    /*****************************/

    ScreenPage(
        onBack = { navController.popBackStack() },
        pullRefreshEnabled = true,
        onRefresh = {
            fireEvent(
                MoviesEvents.Search(query, LocalizationHelper.fullLocal(lang))
            )
        }
    ) {
        searchPagedItems?.let { medias ->
            if (medias.itemCount != 0) {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    items(medias.itemCount) { index ->
                        medias[index]?.let { media ->
                            MediaItem(
                                mediaItem = media
                            ) {
                                val encodedBackdrop = URLEncoder.encode(media.backdropPath, "utf-8")
                                val encodedPoster = URLEncoder.encode(media.posterPath, "utf-8")

                                if (media.mediaType == "tv") {
                                    navController.navigate(
                                        MainScreens.ShowDetails(
                                            show = media.toShowDisplay().copy(
                                                backdropPath = encodedBackdrop,
                                                posterPath = encodedPoster
                                            )
                                        )
                                    )
                                } else {
                                    navController.navigate(
                                        MainScreens.MovieDetails(
                                            movie = media.toMovieDisplay().copy(
                                                backdropPath = encodedBackdrop,
                                                posterPath = encodedPoster
                                            )
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            } else {
                NoContentView()
            }
        }

        with(state) {
            this.error?.let { error ->
                ErrorView(errorText = error) {
                    fireEvent(
                        MoviesEvents.Search(query, LocalizationHelper.fullLocal(lang))
                    )
                }
            }

            if (this.loading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Unspecified)
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    LoadingIndicatorRotating(false)
                }
            }
        }
    }
}

@Preview(showSystemUi = false, showBackground = true, locale = "en")
@Composable
fun PreviewSearchScreen() {
    SearchScreen(
        navController = rememberNavController(),
        state = SearchState(),
        fireEvent = {},
        query = ""
    )
}