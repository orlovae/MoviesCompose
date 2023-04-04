package ru.alexandrorlov.moviescompose.data.remote

import ru.alexandrorlov.moviescompose.config.DataRemoteConfig.ERROR_REMOTE_ACTOR
import ru.alexandrorlov.moviescompose.config.DataRemoteConfig.ERROR_REMOTE_CONFIGURATION
import ru.alexandrorlov.moviescompose.config.DataRemoteConfig.ERROR_REMOTE_GENRE
import ru.alexandrorlov.moviescompose.config.DataRemoteConfig.ERROR_REMOTE_MOVIE_DETAIL
import ru.alexandrorlov.moviescompose.config.DataRemoteConfig.ERROR_REMOTE_MOVIE_POPULAR
import ru.alexandrorlov.moviescompose.di.AppScope
import javax.inject.Inject

@AppScope
class TMDBRemoteDataSource @Inject constructor(private val api: TMDBApiService) :
    TMDBRemoteDataSourceBase() {
    suspend fun getResultFromNetworkMoviePopularList(): Result<Any> {
        return safeApiCall(
            call = { api.getResultFromNetworkMoviePopularList() },
            errorMessage = ERROR_REMOTE_MOVIE_POPULAR
        )
    }

    suspend fun getResultMovieDetailNetwork(movieId: Int): Result<Any> {
        return safeApiCall(
            call = { api.getMovieDetailsNetwork(movieId) },
            errorMessage = ERROR_REMOTE_MOVIE_DETAIL
        )
    }

    suspend fun getResultListActorsNetwork(movieId: Int): Result<Any> {
        return safeApiCall(
            call = { api.getActorsNetwork(movieId) },
            errorMessage = ERROR_REMOTE_ACTOR
        )
    }

    suspend fun getResultConfiguration(): Result<Any> {
        return safeApiCall(
            call = { api.getConfiguration() },
            errorMessage = ERROR_REMOTE_CONFIGURATION
        )
    }

    suspend fun getResultLanguages(): Result<Any> {
        return safeApiCall(
            call = { api.getLanguages() },
            errorMessage = ERROR_REMOTE_CONFIGURATION
        )
    }

    suspend fun getResultGenreNetworkList(): Result<Any> {
        return safeApiCall(
            call = { api.getGenres() },
            errorMessage = ERROR_REMOTE_GENRE
        )
    }
}