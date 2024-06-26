package me.abuzaid.movies.viewmodels

/**
 * Created by "Mohamad Abuzaid" on 08/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
sealed class MoviesEvents {
    data class FetchPopular(val lang: String) : MoviesEvents()
    data class FetchMovies(val lang: String) : MoviesEvents()
    data class FetchShows(val lang: String) : MoviesEvents()
    data class Search(val query: String, val lang: String) : MoviesEvents()
}