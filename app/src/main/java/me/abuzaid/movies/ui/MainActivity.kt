package me.abuzaid.movies.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import me.abuzaid.movies.utils.storage.ILocalPreferencesStorage
import org.koin.android.ext.android.inject

/**
 * Created by "Mohamad Abuzaid" on 01/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavHostController
    private val prefs: ILocalPreferencesStorage by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            navController = rememberNavController()
        }
    }
}