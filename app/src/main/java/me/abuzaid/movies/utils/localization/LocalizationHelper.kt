package me.abuzaid.movies.utils.localization

import me.abuzaid.movies.R

/**
 * Created by "Mohamad Abuzaid" on 01/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
object LocalizationHelper {
    const val AR = "ar"
    private const val EN = "en"

    val langOptions = listOf("العربية", "English")

    fun code(lang: String): String {
        return when (lang) {
            langOptions[0] -> AR
            langOptions[1] -> EN
            else -> AR
        }
    }

    fun icon(lang: String): Int {
        return when (lang) {
            langOptions[0] -> R.drawable.ic_arabic_lang
            langOptions[1] -> R.drawable.ic_english_lang
            else -> R.drawable.ic_arabic_lang
        }
    }

    fun lang(code: String): String {
        return when (code) {
            AR -> langOptions[0]
            EN -> langOptions[1]
            else -> langOptions[0]
        }
    }

    fun country(code: String): String {
        return when (code) {
            AR -> "EG"
            EN -> "GB"
            else -> "EG"
        }
    }

    fun fullLocal(code: String): String {
        return when (code) {
            AR -> "ar-EG"
            EN -> "en-GB"
            else -> "ar-EG"
        }
    }
}