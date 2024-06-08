package me.abuzaid.movies.data.network.services

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import me.abuzaid.movies.data.network.models.MovieRemote
import me.abuzaid.movies.data.network.models.SearchRemote
import me.abuzaid.movies.data.network.models.ShowRemote
import me.abuzaid.movies.data.network.models.wrappers.RemoteResponse

/**
 * Created by "Mohamad Abuzaid" on 26/05/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
class ApiServices(private val client: HttpClient) {

    companion object {
        private const val FETCH_POPULAR_MOVIES = "discover/movie"
        private const val FETCH_MOVIES = "discover/movie"
        private const val FETCH_SHOWS = "discover/tv"
        private const val SEARCH = "search/multi"
    }

    suspend fun fetchPopularMovies(lang: String): RemoteResponse<List<MovieRemote>> =
        client.get(FETCH_POPULAR_MOVIES) {
            parameter("include_adult", false)
            parameter("include_video", false)
            parameter("language", lang)
            parameter("page", 1)
            parameter("sort_by", "popularity.desc")
        }.body()

    suspend fun fetchMovies(lang: String, page: Int): RemoteResponse<List<MovieRemote>> =
        client.get(FETCH_MOVIES) {
            parameter("include_adult", false)
            parameter("include_video", false)
            parameter("language", lang)
            parameter("page", page)
            parameter("sort_by", "vote_average.desc")
        }.body()

    suspend fun fetchTvShows(lang: String, page: Int): RemoteResponse<List<ShowRemote>> =
        client.get(FETCH_SHOWS) {
            parameter("include_adult", false)
            parameter("include_video", false)
            parameter("language", lang)
            parameter("page", page)
            parameter("sort_by", "vote_average.desc")
        }.body()

    suspend fun search(query: String, lang: String, page: Int): RemoteResponse<List<SearchRemote>> =
        client.get(SEARCH) {
            parameter("query", query)
            parameter("include_adult", false)
            parameter("language", lang)
            parameter("page", page)
        }.body()
}