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
import me.abuzaid.movies.navigation.MainScreens
import me.abuzaid.movies.ui.composables.ShowItem
import me.abuzaid.movies.ui.composables.loaders.ErrorView
import me.abuzaid.movies.ui.composables.loaders.LoadingIndicatorRotating
import me.abuzaid.movies.ui.composables.loaders.NoContentView
import me.abuzaid.movies.ui.composables.pages.ScreenPage
import me.abuzaid.movies.utils.LocalLang
import me.abuzaid.movies.utils.localization.LocalizationHelper
import me.abuzaid.movies.viewmodels.MoviesEvents
import me.abuzaid.movies.viewmodels.ShowsState
import java.net.URLEncoder

/**
 * Created by "Mohamad Abuzaid" on 01/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
@Composable
fun ShowsScreen(
    navController: NavController,
    state: ShowsState,
    fireEvent: (MoviesEvents) -> Unit
) {
    val lang = LocalLang.current

    val showsPagedItems = state.success?.collectAsLazyPagingItems()

    /*****************************/

    LaunchedEffect(key1 = state) {
        if (state.success == null) {
            fireEvent(
                MoviesEvents.FetchShows(LocalizationHelper.fullLocal(lang))
            )
        }
    }
    /*****************************/

    ScreenPage(
        onBack = { navController.popBackStack() },
        pullRefreshEnabled = true,
        onRefresh = {
            fireEvent(
                MoviesEvents.FetchShows(LocalizationHelper.fullLocal(lang))
            )
        }
    ) {
        showsPagedItems?.let { shows ->
            if (shows.itemCount != 0) {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    items(shows.itemCount) { index ->
                        shows[index]?.let { show ->
                            ShowItem(
                                showItem = show
                            ) {
                                val encodedBackdrop = URLEncoder.encode(show.backdropPath, "utf-8")
                                val encodedPoster = URLEncoder.encode(show.posterPath, "utf-8")
                                navController.navigate(
                                    MainScreens.ShowDetails(
                                        show = show.copy(
                                            backdropPath = encodedBackdrop,
                                            posterPath = encodedPoster
                                        )
                                    )
                                )
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
                        MoviesEvents.FetchShows(LocalizationHelper.fullLocal(lang))
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
fun PreviewShowsScreen() {
    ShowsScreen(
        navController = rememberNavController(),
        state = ShowsState(),
        fireEvent = {}
    )
}