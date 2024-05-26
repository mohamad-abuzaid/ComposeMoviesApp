package me.abuzaid.movies.data.database.converters

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import timber.log.Timber

/**
 * Created by "Mohamad Abuzaid" on 25/05/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
class DataTypeConverter {

    @TypeConverter
    fun fromMap(value: Map<String, String>?): String {
        return value?.let { Json.encodeToString(it) } ?: ""
    }

    @TypeConverter
    fun toMap(value: String): Map<String, String>? {

        return try {
            Json.decodeFromString(value)
        } catch (ex: Exception) {
            Timber.tag("DataTypeConverter").e(ex)
            null
        }
    }
}