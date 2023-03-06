package ru.alexandrorlov.moviescompose.network

//TODO перенести значения error Message в ресурсы и м.б. подробнее расписать.
class NetworkClientNetwork(private val api: TmdbApi) : NetworkClientBase() {

    suspend fun getResultListMoviePopularNetwork(): Result<Any> {
        return safeApiCall(
            call = { api.getMoviesPopular() },
            errorMessage = "Error Fetching Popular Movies"
        )
    }

    suspend fun getResultMovieDetailNetwork(movieId: Int): Result<Any> {
        return safeApiCall(
            call = { api.getMovieDetailsNetwork(movieId) },
            errorMessage = "Error Fetching Movie Detail"
        )
    }

    suspend fun getResultListActorsNetwork(movieId: Int): Result<Any> {
        return safeApiCall(
            call = { api.getActorsNetwork(movieId) },
            errorMessage = "Error Fetching Actors"
        )
    }
    object Singleton {
        val instance = NetworkClientNetwork(RetrofitModule.tmdbApi)
    }
}