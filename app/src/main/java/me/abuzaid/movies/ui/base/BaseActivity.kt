package me.abuzaid.movies.ui.base

import android.content.Context
import androidx.activity.ComponentActivity
import me.abuzaid.movies.utils.localization.AppContextWrapper
import me.abuzaid.movies.utils.localization.LocalizationHelper
import me.abuzaid.movies.utils.storage.ILocalPreferencesStorage
import me.abuzaid.movies.utils.storage.Preference
import org.koin.android.ext.android.inject
import java.util.Locale

/**
 * Created by "Mohamad Abuzaid" on 01/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
open class BaseActivity : ComponentActivity() {
    private val prefs: ILocalPreferencesStorage by inject()

    override fun attachBaseContext(baseContext: Context) {
        val lang = prefs.getString(Preference.LANGUAGE_KEY, LocalizationHelper.AR)
        val initialLocale = Locale(lang, LocalizationHelper.country(lang))

        super.attachBaseContext(AppContextWrapper.wrap(baseContext, initialLocale))
    }
}