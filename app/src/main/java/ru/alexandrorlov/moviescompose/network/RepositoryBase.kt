package ru.alexandrorlov.moviescompose.network

import retrofit2.Response
import java.io.IOException

open class RepositoryBase {

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): Result<T> {
        return safeApiResult(call, errorMessage)
    }

    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): Result<T> {
        val response = call.invoke()
        if (response.isSuccessful) return Result.Success(response.body())
//TODO прописать возможные варианты когда респонс суксесс, но пришледшее боди - говно
        return Result.Error(
            IOException(
                "Error Occurred during getting safe Api result," +
                        " Custom ERROR - $errorMessage"
            ).toString()
        )
    }
}