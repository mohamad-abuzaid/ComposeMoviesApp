package me.abuzaid.movies.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import me.abuzaid.movies.models.MovieDisplay
import me.abuzaid.movies.models.ShowDisplay
import me.abuzaid.movies.navigation.navtypes.parcelableType
import me.abuzaid.movies.ui.screens.HomeScreen
import me.abuzaid.movies.ui.screens.LanguageSelectScreen
import me.abuzaid.movies.ui.screens.MovieDetailsScreen
import me.abuzaid.movies.ui.screens.MoviesScreen
import me.abuzaid.movies.ui.screens.SearchScreen
import me.abuzaid.movies.ui.screens.ShowDetailsScreen
import me.abuzaid.movies.ui.screens.ShowsScreen
import me.abuzaid.movies.ui.screens.SplashScreen
import me.abuzaid.movies.utils.storage.ILocalPreferencesStorage
import me.abuzaid.movies.viewmodels.MoviesViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import kotlin.reflect.typeOf

/**
 * Created by "Mohamad Abuzaid" on 01/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController,
) {
    navigation<MAIN>(
        startDestination = MainScreens.Splash
    ) {
        composable<MainScreens.Splash> {
            val prefs: ILocalPreferencesStorage = koinInject()

            SplashScreen(navController, prefs)
        }

        composable<MainScreens.LanguageSelect> {
            val prefs: ILocalPreferencesStorage = koinInject()

            LanguageSelectScreen(navController, prefs)
        }

        composable<MainScreens.Home> {
            val viewModel: MoviesViewModel = koinViewModel()
            val state = viewModel.popularState
            val event = viewModel::onEvent

            HomeScreen(navController, state, event)
        }

        composable<MainScreens.Movies> {
            val viewModel: MoviesViewModel = koinViewModel()
            val state = viewModel.moviesState
            val event = viewModel::onEvent

            MoviesScreen(navController, state, event)
        }

        composable<MainScreens.Shows> {
            val viewModel: MoviesViewModel = koinViewModel()
            val state = viewModel.showsState
            val event = viewModel::onEvent

            ShowsScreen(navController, state, event)
        }

        composable<MainScreens.Search> { entry ->
            val viewModel: MoviesViewModel = koinViewModel()
            val state = viewModel.searchState
            val event = viewModel::onEvent
            val query = entry.toRoute<MainScreens.Search>().query

            SearchScreen(navController, state, event, query)
        }

        composable<MainScreens.MovieDetails>(
            typeMap = mapOf(typeOf<MovieDisplay>() to parcelableType<MovieDisplay>(MovieDisplay.serializer()))
        ) { entry ->
            val movie = entry.toRoute<MainScreens.MovieDetails>().movie

            MovieDetailsScreen(navController, movie)
        }

        composable<MainScreens.ShowDetails>(
            typeMap = mapOf(typeOf<ShowDisplay>() to parcelableType<ShowDisplay>(ShowDisplay.serializer()))
        ) { entry ->
            val show = entry.toRoute<MainScreens.ShowDetails>().show

            ShowDetailsScreen(navController, show)
        }
    }
}
