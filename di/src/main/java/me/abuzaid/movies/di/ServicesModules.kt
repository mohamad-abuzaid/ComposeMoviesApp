package me.abuzaid.movies.di

import io.ktor.client.HttpClient
import me.abuzaid.movies.data.network.di.networkModule
import me.abuzaid.movies.data.network.services.ApiServices
import org.koin.dsl.module

/**
 * Created by "Mohamad Abuzaid" on 25/05/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
val servicesModule = module {
    includes(networkModule)

    single { ApiServices(get<HttpClient>()) }
}