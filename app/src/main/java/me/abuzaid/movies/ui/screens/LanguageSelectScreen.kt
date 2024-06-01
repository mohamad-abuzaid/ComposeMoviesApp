package me.abuzaid.movies.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.abuzaid.movies.R
import me.abuzaid.movies.navigation.MainScreens
import me.abuzaid.movies.ui.composables.buttons.MainRoundedButton
import me.abuzaid.movies.ui.composables.buttons.RadioButtonStroke
import me.abuzaid.movies.ui.composables.pages.ScreenPage
import me.abuzaid.movies.utils.findActivity
import me.abuzaid.movies.utils.localization.LocalizationHelper
import me.abuzaid.movies.utils.storage.ILocalPreferencesStorage
import me.abuzaid.movies.utils.storage.Preference

/**
 * Created by "Mohamad Abuzaid" on 01/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
@Composable
fun LanguageSelectScreen(
    navController: NavController,
    prefs: ILocalPreferencesStorage?
) {
    val context = LocalContext.current
    val initialLangCode =
        prefs?.getString(Preference.LANGUAGE_KEY, LocalizationHelper.AR) ?: LocalizationHelper.AR
    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(LocalizationHelper.lang(initialLangCode))
    }

    if (LocalizationHelper.code(selectedOption) != initialLangCode) {
        val code = LocalizationHelper.code(selectedOption)
        prefs?.putString(Preference.LANGUAGE_KEY, code)

        context.findActivity()?.finish()
        context.findActivity()?.intent?.let {
            context.findActivity()?.startActivity(it)
        }
    }

    ScreenPage(
        onRefresh = { }
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.weight(1f))
            Text(
                stringResource(R.string.language_title),
                style = MaterialTheme.typography.displayLarge,
                color = Color.White
            )

            Spacer(Modifier.height(25.dp))
            Text(
                stringResource(R.string.language_subtitle_ar),
                style = MaterialTheme.typography.displayMedium,
                color = Color.White
            )

            Spacer(Modifier.height(10.dp))
            Text(
                stringResource(R.string.language_subtitle_en),
                style = MaterialTheme.typography.displayMedium,
                color = Color.White
            )

            Spacer(Modifier.weight(1f))
            LocalizationHelper.langOptions.forEach { text ->
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    RadioButtonStroke(
                        text = text,
                        iconResId = LocalizationHelper.icon(text),
                        selectedOption = selectedOption,
                        onOptionSelected = onOptionSelected
                    )
                }
            }

            Spacer(Modifier.weight(1f))
            MainRoundedButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.save)
            ) {
                prefs?.putBoolean(Preference.FIRST_TIME_LAUNCH, false)
                navController.navigate(route = MainScreens.Home.route)
            }
        }
    }
}

@Preview(showSystemUi = false, showBackground = true, locale = "en")
@Composable
fun PreviewLanguageSelectScreen() {
    LanguageSelectScreen(
        navController = rememberNavController(),
        prefs = null
    )
}