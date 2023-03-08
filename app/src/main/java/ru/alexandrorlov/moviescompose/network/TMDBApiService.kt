package ru.alexandrorlov.moviescompose.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.alexandrorlov.moviescompose.config.AppConfig

interface TMDBApiService {

    //TODO Можно на начальном этапе запросить языки /configuration/languages, и сделать страницу или диалог или функцию на экране, где можно выбрать язык. и передавать язык сюда

    @GET("movie/popular?")
    suspend fun getResultFromNetworkMoviePopularList(
        @Query("api_key") api_key: String = AppConfig.TMDB_API_KEY,
        @Query("language") language: String = "ru",
        @Query("page") page: Int = 1
    ): Response<ResultFromNetworkMoviePopularList>

    @GET("movie/{movie_id}/credits?")
    suspend fun getActorsNetwork(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = AppConfig.TMDB_API_KEY,
        @Query("language") language: String = "ru"
    ): Response<ResultActorNetworkList>

    @GET("configuration")
    suspend fun getConfiguration(
        @Query("api_key") api_key: String = AppConfig.TMDB_API_KEY
    ): Response<ConfigurationDTO>

    @GET("movie/{movie_id}?")
    suspend fun getMovieDetailsNetwork(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = AppConfig.TMDB_API_KEY,
        @Query("language") language: String = "ru",
        @Query("append_to_response") append: String = "release_dates"
    ): Response<MovieDetailsNetwork>

    @GET("configuration/languages?")
    suspend fun getLanguages(
        @Query("api_key") api_key: String = AppConfig.TMDB_API_KEY
    ): Response<Languages>
}