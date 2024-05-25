package me.abuzaid.movies.data.database.converters

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
 * Created by "Mohamad Abuzaid" on 25/05/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
class DataTypeConverter {

    @TypeConverter
    fun fromMap(value: Map<String, String>?): String {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val type =
            Types.newParameterizedType(Map::class.java, String::class.java, String::class.java)
        val jsonAdapter: JsonAdapter<Map<String, String>> = moshi.adapter(type)

        return jsonAdapter.toJson(value)
    }

    @TypeConverter
    fun toMap(value: String): Map<String, String>? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val type =
            Types.newParameterizedType(Map::class.java, String::class.java, String::class.java)
        val jsonAdapter: JsonAdapter<Map<String, String>> = moshi.adapter(type)

        return jsonAdapter.fromJson(value)
    }
}