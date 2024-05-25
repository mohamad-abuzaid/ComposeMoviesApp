package me.abuzaid.movies.di

import me.abuzaid.movies.data.AppDatabase
import org.koin.dsl.module

/**
 * Created by "Mohamad Abuzaid" on 25/05/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
val databaseModule = module {
    single { AppDatabase.getInstance(context = get()) }

    single { get<AppDatabase>().moviesDAO() }
}