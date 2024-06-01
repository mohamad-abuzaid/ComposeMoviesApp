package me.abuzaid.movies.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import me.abuzaid.movies.navigation.Graph
import me.abuzaid.movies.navigation.mainNavGraph
import me.abuzaid.movies.ui.base.BaseActivity
import me.abuzaid.movies.ui.theme.MoviesTheme
import me.abuzaid.movies.utils.LocalLang
import me.abuzaid.movies.utils.storage.ILocalPreferencesStorage
import me.abuzaid.movies.utils.storage.Preference
import org.koin.android.ext.android.inject

/**
 * Created by "Mohamad Abuzaid" on 01/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
class MainActivity : BaseActivity() {
    private lateinit var navController: NavHostController
    private val prefs: ILocalPreferencesStorage by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lang = prefs.getString(Preference.LANGUAGE_KEY, "ar")

        setContent {
            navController = rememberNavController()

            CompositionLocalProvider(
                LocalLang provides lang
            ) {
                MoviesTheme {
                    NavHost(navController = navController, startDestination = Graph.MAIN) {
                        mainNavGraph(navController)
                    }
                }
            }
        }
    }
}