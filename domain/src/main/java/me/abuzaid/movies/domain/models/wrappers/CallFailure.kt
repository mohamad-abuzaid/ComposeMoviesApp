package me.abuzaid.movies.domain.models.wrappers

/**
 * Created by "Mohamad Abuzaid" on 26/05/2024.
 * Email: mabuzaid@sure.com.sa
 */
open class CallFailure(val errorModel: ErrorModel) : Throwable()