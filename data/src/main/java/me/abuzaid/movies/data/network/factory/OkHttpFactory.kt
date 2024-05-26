package me.abuzaid.movies.data.network.factory

import me.abuzaid.movies.data.network.interceptors.RequestHeaderInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Created by "Mohamad Abuzaid" on 25/05/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
object OkHttpFactory {
    fun getInstance(
        requestHeaderInterceptor: RequestHeaderInterceptor
    ): OkHttpClient {
        synchronized(this) {
            val okHttpClientBuilder = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addNetworkInterceptor(requestHeaderInterceptor)

            return okHttpClientBuilder.build()
        }
    }
}