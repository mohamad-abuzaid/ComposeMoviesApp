package me.abuzaid.movies.di

import me.abuzaid.movies.data.repositories.MoviesRepositoryImpl
import me.abuzaid.movies.domain.repositories.MoviesRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by "Mohamad Abuzaid" on 25/05/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
val repositoriesModule = module {
    single<MoviesRepository> {
        MoviesRepositoryImpl(
            moviesApiServices = get(),
            dispatcher = get(named("ioDispatcher"))
        )
    }
}