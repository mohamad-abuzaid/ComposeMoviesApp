package me.abuzaid.movies.data.network.di

import io.ktor.client.HttpClient
import me.abuzaid.movies.data.BuildConfig
import me.abuzaid.movies.data.network.factory.KtorClientFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by "Mohamad Abuzaid" on 25/05/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
val networkModule = module {
    single(named("baseUrl")) { BuildConfig.BASE_URL }
    single(named("accessToken")) { BuildConfig.TOKEN }

    single {
        KtorClientFactory.getInstance(
            baseUrl = get(named("baseUrl")),
            token = get(named("accessToken"))
        )
    }
}