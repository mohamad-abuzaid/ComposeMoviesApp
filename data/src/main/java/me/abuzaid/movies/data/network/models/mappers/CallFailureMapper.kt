package me.abuzaid.movies.data.network.models.mappers

import android.net.http.HttpException
import me.abuzaid.movies.domain.models.wrappers.CallFailure
import me.abuzaid.movies.domain.models.wrappers.ErrorModel
import me.abuzaid.movies.domain.models.wrappers.error_code.CallErrorCodes
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by "Mohamad Abuzaid" on 26/05/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
class CustomError(code: Int, errorMessage: String, errorDetails: String) :
    CallFailure(
        ErrorModel(
            code = code,
            errorMessage = errorMessage,
            errorDetails = errorDetails
        )
    )

class UnAuthorizedAccess(code: Int) :
    CallFailure(
        ErrorModel(
            code = code,
            errorMessage = CallErrorCodes.UNAUTHORIZED_ACCESS,
            errorDetails = "UnAuthorized Access"
        )
    )

class ServiceUnAvailable(code: Int) :
    CallFailure(
        ErrorModel(
            code = code,
            errorMessage = CallErrorCodes.SERVICE_UNAVAILABLE,
            errorDetails = "Service UnAvailable"
        )
    )

class TimeOut : CallFailure(
    ErrorModel(
        code = -1,
        errorMessage = CallErrorCodes.TIMEOUT,
        errorDetails = "TimeOut"
    )
)

class InvalidInput(code: Int) :
    CallFailure(
        ErrorModel(
            code = code,
            errorMessage = CallErrorCodes.INVALID_INPUT,
            errorDetails = "Invalid Input"
        )
    )

class ServerError(code: Int) :
    CallFailure(
        ErrorModel(
            code = code,
            errorMessage = CallErrorCodes.SERVER_ERROR,
            errorDetails = "Server Error"
        )
    )

class ConnectionError :
    CallFailure(
        ErrorModel(
            code = -1,
            errorMessage = CallErrorCodes.CONNECTION_ERROR,
            errorDetails = "Connection Error"
        )
    )

class UnKnownError(code: Int = -1) :
    CallFailure(
        ErrorModel(
            code = code,
            errorMessage = CallErrorCodes.UNKOWN_ERROR,
            errorDetails = "UnKnown Error"
        )
    )

object MapThrowableToCallFailure {
    fun map(throwable: Throwable): CallFailure {
        try {
            when (throwable) {
//                is HttpException -> {
//                    val httpException: HttpException = throwable
//                    return when (val code: Int = httpException.hashCode()) {
//                        401 -> UnAuthorizedAccess(code)
//                        503 -> ServiceUnAvailable(code)
//                        400 -> InvalidInput(code)
//                        500 -> ServerError(code)
//                        else -> UnKnownError(code)
//                    }
//                }

                is SocketTimeoutException -> {
                    return TimeOut()
                }

                is UnknownHostException -> {
                    return ConnectionError()
                }

                is IOException -> {
                    return ConnectionError()
                }

                else -> {
                    return UnKnownError()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return UnKnownError()
        }
    }
}