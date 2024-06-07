package me.abuzaid.movies.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import me.abuzaid.movies.R
import me.abuzaid.movies.navigation.MainScreens
import me.abuzaid.movies.utils.storage.ILocalPreferencesStorage
import me.abuzaid.movies.utils.storage.Preference

/**
 * Created by "Mohamad Abuzaid" on 01/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
@Composable
fun SplashScreen(
    navController: NavController,
    prefs: ILocalPreferencesStorage?
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.splash_background),
            contentDescription = "Splash Image",
            contentScale = ContentScale.FillBounds
        )
    }

    val currentTime = System.currentTimeMillis()
    val firstTime = prefs?.getBoolean(Preference.FIRST_TIME_LAUNCH, true) ?: false

    LaunchedEffect(key1 = currentTime) {
        delay(3000)
        if (firstTime) {
            navController.navigate(MainScreens.LanguageSelect)
        } else {
            navController.navigate(MainScreens.Home)
        }
    }
}

@Preview(showSystemUi = false, showBackground = true, locale = "en")
@Composable
fun PreviewSplashScreenScreen() {
    SplashScreen(
        navController = rememberNavController(),
        prefs = null
    )
}