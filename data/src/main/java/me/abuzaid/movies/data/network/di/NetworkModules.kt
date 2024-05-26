package me.abuzaid.movies.data.network.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import me.abuzaid.movies.data.BuildConfig
import me.abuzaid.movies.data.network.factory.KtorClientFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by "Mohamad Abuzaid" on 25/05/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
val networkModule = module {
    includes(okhttpModule)

    single(named("dataBaseUrl")) { BuildConfig.BASE_URL }
    single(named("accessToken")) { BuildConfig.TOKEN }

    single<Moshi> {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        KtorClientFactory.getInstance(
            baseUrl = get(named("dataBaseUrl")),
            okHttpClient = get(),
            parser = get()
        )
    }
}