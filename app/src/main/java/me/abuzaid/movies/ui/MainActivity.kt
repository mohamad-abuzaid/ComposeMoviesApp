package me.abuzaid.movies.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import me.abuzaid.movies.R

/**
 * Created by "Mohamad Abuzaid" on 01/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = if (prefs.getString(Preference.USER_INFO, "").isNotEmpty())
            Gson().fromJson(prefs.getString(Preference.USER_INFO, ""), LoginDisplay::class.java)
        else null

        val lang = prefs.getString(Preference.LANGUAGE_KEY, "ar")

        setContent {
            navController = rememberNavController()
            val token by remember { mutableStateOf(prefs.getString(Preference.USER_TOKEN, "")) }

            CompositionLocalProvider(
                LocalUser provides user,
                LocalLang provides lang,
                LocalToken provides token
            ) {
                SCISPTheme {
                    NavHost(navController = navController, startDestination = Graph.MAIN) {
                        mainNavGraph(navController)
                    }
                }
            }
        }
    }
}