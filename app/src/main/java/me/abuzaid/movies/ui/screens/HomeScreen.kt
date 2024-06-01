package me.abuzaid.movies.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.abuzaid.movies.ui.composables.pages.ScreenPage

/**
 * Created by "Mohamad Abuzaid" on 01/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
@Composable
fun HomeScreen(
    navController: NavController
) {
    ScreenPage(
        pullRefreshEnabled = true,
        onRefresh = { }
    ) {

    }
}

@Preview(showSystemUi = false, showBackground = true, locale = "en")
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        navController = rememberNavController()
    )
}