package me.abuzaid.movies.navigation

import kotlinx.serialization.Serializable
import me.abuzaid.movies.models.MovieDisplay

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
    data object Shows : MainScreens()

    @Serializable
    data class MovieDetails(val movie: MovieDisplay) : MainScreens()
}