package ru.alexandrorlov.moviescompose.network

class RepositoryNetworkConfiguration(private val api: TmdbApi) : RepositoryBase() {
    private var resultConfiguration: Result<Configuration>? = null

    suspend fun getResultConfiguration(): Result<Configuration> {
        resultConfiguration ?: run {
            val response = safeApiCall(
                call = { api.getConfiguration() },
                errorMessage = "Error Fetching Configuration Api"
            )
            resultConfiguration = response
        }
        return resultConfiguration as Result<Configuration>
    }

    object Singleton {
        val instance = RepositoryNetworkConfiguration(RetrofitModule.tmdbApi)
    }
}