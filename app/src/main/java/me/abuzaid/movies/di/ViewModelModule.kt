package me.abuzaid.movies.di

import me.abuzaid.movies.viewmodels.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Created by "Mohamad Abuzaid" on 25/05/2024.
 * Email: m.abuzaid.ali@gmail.com
 */

val viewModelsModule = module {
    viewModelOf(::MoviesViewModel)
}