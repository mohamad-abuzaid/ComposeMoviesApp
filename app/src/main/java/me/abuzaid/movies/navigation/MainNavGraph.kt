package me.abuzaid.movies.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation

/**
 * Created by "Mohamad Abuzaid" on 01/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController,
) {
    navigation<MainScreens.Splash>(
        startDestination = MainScreens.Splash
    ) {

    }
}
