package me.abuzaid.movies.data.network.di

import me.abuzaid.movies.data.network.factory.OkHttpFactory
import me.abuzaid.movies.data.network.interceptors.RequestHeaderInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by "Mohamad Abuzaid" on 25/05/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
val okhttpModule = module {
    single { RequestHeaderInterceptor(token = get(named("accessToken"))) }

    single { OkHttpFactory.getInstance(requestHeaderInterceptor = get()) }
}