package ru.alexandrorlov.moviescompose.config

object Network {
    const val ERROR_POSTER = "Заглушка для poster is null"
    const val ERROR_BACKDROP = "Заглушка для backdrop is null"
    const val ERROR_ACTOR = "Заглушка для actor is null"

    const val LANGUAGE_FOR_PG = "US"

    const val ERROR_PG = "PG_IS_NULL"

    fun getTopSecretURL(): String {
        return baseURL + listLogoSizes[mediumLogo]
    }
    //https://developers.themoviedb.org/3/configuration/get-api-configuration
    private val baseURL = "https://image.tmdb.org/t/p/"

    private val listLogoSizes = arrayOf(
        "w45",
        "w92",
        "w154",
        "w185",
        "w300",
        "w500",
        "original"
    )
    private val sizeBackdrop = arrayOf(
        "w92",
        "w154",
        "w185",
        "w342",
        "w500",
        "w780",
        "original"
    )
    private val sizeProfile = arrayOf(
        "w45",
        "w185",
        "h632",
        "original"
    )
    private val mediumLogo = 5
    const val BACKDROP_SIZE = 1
    const val PROFILE_SIZE = 1
}