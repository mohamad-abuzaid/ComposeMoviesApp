package me.abuzaid.movies.domain.models.wrappers

/**
 * Created by "Mohamad Abuzaid" on 26/05/2024.
 * Email: mabuzaid@sure.com.sa
 */
data class NetworkCallResult<T, E>(val value: T? = null, val error: E? = null)