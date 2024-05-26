package me.abuzaid.movies.data.network.services

import io.ktor.client.HttpClient
import io.ktor.client.request.get

/**
 * Created by "Mohamad Abuzaid" on 26/05/2024.
 * Email: mabuzaid@sure.com.sa
 */
class ApiServices(private val client: HttpClient) {

    companion object {
        private const val FETCH_MOVIES = "discover/movie"
    }

    suspend fun fetchMovies() = client.get(FETCH_MOVIES)
}