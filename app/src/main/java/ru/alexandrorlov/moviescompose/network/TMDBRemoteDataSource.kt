package ru.alexandrorlov.moviescompose.network

//TODO перенести значения error Message в ресурсы и м.б. подробнее расписать.
class TMDBRemoteDataSource(private val api: TMDBApiService) : TMDBRemoteDataSourceBase() {
    suspend fun getResultFromNetworkMoviePopularList(): Result<Any> {
        return safeApiCall(
            call = { api.getResultFromNetworkMoviePopularList() },
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

    suspend fun getResultConfiguration(): Result<Any> {
        return safeApiCall(
            call = { api.getConfiguration() },
            errorMessage = "Error Fetching Configuration Api"
        )
    }

    suspend fun getResultLanguages(): Result<Any> {
        return safeApiCall(
            call = { api.getLanguages() },
            errorMessage = "Error Fetching Configuration Api"
        )
    }

    object Singleton {
        val instance = TMDBRemoteDataSource(RetrofitModule.tmdbApiService)
    }
}