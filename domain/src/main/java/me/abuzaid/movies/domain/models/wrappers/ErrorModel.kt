package me.abuzaid.movies.domain.models.wrappers

/**
 * Created by "Mohamad Abuzaid" on 26/05/2024.
 * Email: mabuzaid@sure.com.sa
 */

data class ErrorModel(
    val code: Int,
    val errorMessage: String,
    val errorDetails: String,
)