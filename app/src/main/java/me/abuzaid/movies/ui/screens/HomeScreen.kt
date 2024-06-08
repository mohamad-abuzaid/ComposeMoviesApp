package me.abuzaid.movies.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.abuzaid.movies.R
import me.abuzaid.movies.navigation.MainScreens
import me.abuzaid.movies.ui.composables.MediaItem
import me.abuzaid.movies.ui.composables.inputfields.InputTextField
import me.abuzaid.movies.ui.composables.loaders.ErrorView
import me.abuzaid.movies.ui.composables.loaders.LoadingIndicatorRotating
import me.abuzaid.movies.ui.composables.loaders.NoContentView
import me.abuzaid.movies.ui.composables.pages.ScreenPage
import me.abuzaid.movies.utils.LocalLang
import me.abuzaid.movies.utils.localization.LocalizationHelper
import me.abuzaid.movies.viewmodels.MoviesEvents
import me.abuzaid.movies.viewmodels.PopularState
import java.net.URLEncoder

/**
 * Created by "Mohamad Abuzaid" on 01/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
@Composable
fun HomeScreen(
    navController: NavController,
    state: PopularState,
    fireEvent: (MoviesEvents) -> Unit
) {
    val lang = LocalLang.current

    val searchQuery = remember { mutableStateOf("") }

    /*****************************/

    LaunchedEffect(key1 = state) {
        if (state.success == null) {
            fireEvent(
                MoviesEvents.FetchPopular(LocalizationHelper.fullLocal(lang))
            )
        }
    }
    /*****************************/

    ScreenPage(
        pullRefreshEnabled = true,
        onRefresh = {
            fireEvent(
                MoviesEvents.FetchPopular(LocalizationHelper.fullLocal(lang))
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 25.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Text(
                text = stringResource(id = R.string.search_title),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            InputTextField(
                placeholder = stringResource(R.string.search_title),
                onSearch = { navController.navigate(MainScreens.Search(searchQuery.value)) }
            ) {
                searchQuery.value = it
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.categories_title),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            navController.navigate(MainScreens.Movies)
                        },
                    contentAlignment = Alignment.TopEnd
                ) {
                    Image(
                        modifier = Modifier.width(180.dp),
                        painter = painterResource(id = R.drawable.ic_movies),
                        contentDescription = "Movies Image"
                    )

                    Text(
                        modifier = Modifier.padding(top = 25.dp, end = 10.dp),
                        text = stringResource(id = R.string.movies),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            navController.navigate(MainScreens.Shows)
                        },
                    contentAlignment = Alignment.TopStart
                ) {
                    Image(
                        modifier = Modifier.width(175.dp),
                        painter = painterResource(id = R.drawable.ic_shows),
                        contentDescription = "Shows Image"
                    )

                    Text(
                        modifier = Modifier.padding(top = 25.dp, start = 10.dp),
                        text = stringResource(id = R.string.shows),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.popular_title),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            with(state) {
                this.success?.let { movies ->
                    if (movies.isNotEmpty()) {
                        LazyVerticalGrid(
                            modifier = Modifier.fillMaxSize(),
                            columns = GridCells.Fixed(3),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            items(movies) { movie ->
                                MediaItem(
                                    mediaItem = movie
                                ) {
                                    val encodedBackdrop =
                                        URLEncoder.encode(movie.backdropPath, "utf-8")
                                    val encodedPoster = URLEncoder.encode(movie.posterPath, "utf-8")
                                    navController.navigate(
                                        MainScreens.MovieDetails(
                                            movie = movie.copy(
                                                backdropPath = encodedBackdrop,
                                                posterPath = encodedPoster
                                            )
                                        )
                                    )
                                }
                            }
                        }
                    } else {
                        NoContentView()
                    }
                }

                this.error?.let { error ->
                    ErrorView(errorText = error) {
                        fireEvent(
                            MoviesEvents.FetchPopular(LocalizationHelper.fullLocal(lang))
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
}

@Preview(showSystemUi = false, showBackground = true, locale = "en")
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        navController = rememberNavController(),
        state = PopularState(),
        fireEvent = {}
    )
}