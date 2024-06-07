package me.abuzaid.movies.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import me.abuzaid.movies.ui.screens.LanguageSelectScreen
import me.abuzaid.movies.ui.screens.SplashScreen
import me.abuzaid.movies.utils.storage.ILocalPreferencesStorage
import org.koin.compose.koinInject

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
    }
}
