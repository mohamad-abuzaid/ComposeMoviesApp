package me.abuzaid.movies.navigation

import kotlinx.serialization.Serializable
import me.abuzaid.movies.models.MovieDisplay
import me.abuzaid.movies.models.ShowDisplay

/**
 * Created by "Mohamad Abuzaid" on 01/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
@Serializable
sealed class MainScreens {
    @Serializable
    data object Splash : MainScreens()

    @Serializable
    data object LanguageSelect : MainScreens()

    @Serializable
    data object Home : MainScreens()

    @Serializable
    data object Movies : MainScreens()

    @Serializable
    data object Shows : MainScreens()

    @Serializable
    data class Search(val query: String) : MainScreens()

    @Serializable
    data class MovieDetails(val movie: MovieDisplay) : MainScreens()

    @Serializable
    data class ShowDetails(val show: ShowDisplay) : MainScreens()

    @Serializable
    data object Animation : MainScreens()
}