package me.abuzaid.movies.di

import me.abuzaid.movies.utils.storage.ILocalPreferencesStorage
import me.abuzaid.movies.utils.storage.PreferencesStorage
import org.koin.dsl.module

/**
 * Created by "Mohamad Abuzaid" on 25/05/2024.
 * Email: m.abuzaid.ali@gmail.com
 */

val storageModule = module {
    single<ILocalPreferencesStorage> { PreferencesStorage(context = get()) }
}