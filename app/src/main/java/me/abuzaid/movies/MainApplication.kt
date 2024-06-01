package me.abuzaid.movies

import android.app.Application
import me.abuzaid.movies.di.databaseModule
import me.abuzaid.movies.di.miscModule
import me.abuzaid.movies.di.repositoriesModule
import me.abuzaid.movies.di.servicesModule
import me.abuzaid.movies.di.useCasesModule
import me.abuzaid.movies.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * Created by "Mohamad Abuzaid" on 25/05/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
class MainApplication : Application() {

    override fun onCreate() {
        Timber.d("onCreate()")
        super.onCreate()

        startKoin {

            // Log Koin into Android logger
            androidLogger()

            // Reference Android context
            androidContext(this@MainApplication)

            // Declare modules
            modules(
                listOf(
                    viewModelsModule,
                    useCasesModule,
                    repositoriesModule,
                    miscModule,
                    databaseModule,
                    servicesModule
                )
            )
        }

        if (BuildConfig.DEBUG) {
            // Log Timber into Android logger - only if debug mode
            Timber.plant(Timber.DebugTree())
        }
    }
}