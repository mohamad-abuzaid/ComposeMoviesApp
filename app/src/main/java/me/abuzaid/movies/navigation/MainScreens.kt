package me.abuzaid.movies.navigation

/**
 * Created by "Mohamad Abuzaid" on 01/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
sealed class MainScreens(val route: String) {
    data object SplashScreen : MainScreens("splash_screen")
    data object LanguageSelectScreen : MainScreens("language_select_screen")
    data object HomeScreen : MainScreens("home_screen")
}