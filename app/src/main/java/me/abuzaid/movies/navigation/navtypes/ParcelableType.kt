package me.abuzaid.movies.navigation.navtypes

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

/**
 * Created by "Mohamad Abuzaid" on 07/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
inline fun <reified T : Parcelable> parcelableType(
    serializer: KSerializer<T>,
    isNullableAllowed: Boolean = false,
    json: Json = Json,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, T::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }

    override fun parseValue(value: String): T = json.decodeFromString(value)

    override fun serializeAsValue(value: T): String = json.encodeToString(serializer, value)

    override fun put(bundle: Bundle, key: String, value: T) = bundle.putParcelable(key, value)
}