package me.abuzaid.movies.data.network.factory

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import timber.log.Timber

/**
 * Created by "Mohamad Abuzaid" on 25/05/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
object KtorClientFactory {
    private const val NETWORK_TIME_OUT = 6_000L

    @OptIn(ExperimentalSerializationApi::class)
    fun getInstance(
        baseUrl: String,
        token: String
    ): HttpClient {
        synchronized(this) {
            return HttpClient(Android) {
                install(ContentNegotiation) {
                    json(
                        Json {
                            prettyPrint = true
                            isLenient = true
                            useAlternativeNames = true
                            ignoreUnknownKeys = true
                            encodeDefaults = false
                            explicitNulls = false
                        }
                    )
                }

                install(HttpTimeout) {
                    requestTimeoutMillis = NETWORK_TIME_OUT
                    connectTimeoutMillis = NETWORK_TIME_OUT
                    socketTimeoutMillis = NETWORK_TIME_OUT
                }

                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            Timber.v("Logger Ktor => $message")
                        }
                    }
                    level = LogLevel.ALL
                }

                install(ResponseObserver) {
                    onResponse { response ->
                        Timber.tag("HTTP status:").d(response.status.value.toString())
                    }
                }

                install(DefaultRequest) {
                    header(
                        HttpHeaders.ContentType,
                        ContentType.Application.Json
                    )
                }

                defaultRequest {
                    url {
                        host = baseUrl
                        protocol = URLProtocol.HTTPS
                        path("3/")
                    }

                    bearerAuth(token = token)
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                }
            }
        }
    }
}
