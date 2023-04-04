package ru.alexandrorlov.moviescompose.config

import ru.alexandrorlov.moviescompose.R

object DataRemoteConfig {
    const val ERROR_IMAGE = R.drawable.ic_launcher_error.toString()

    const val LANGUAGE_FOR_PG = "US"

    const val ERROR_PG = "THE RATING IS NOT SET"

    const val SEPARATE_SYMBOL = "|"

    const val ERROR_REMOTE_MOVIE_POPULAR = "Error Fetching Popular Movies"
    const val ERROR_REMOTE_MOVIE_DETAIL = "Error Fetching Movie Detail"
    const val ERROR_REMOTE_ACTOR = "Error Fetching Actors"
    const val ERROR_REMOTE_CONFIGURATION = "Error Fetching Configuration Api"
    const val ERROR_REMOTE_GENRE = "Error Fetching Genre Api"

    const val JSON = "application/json"

    fun getBaseURLGet(): String {
        return BASE_URL_GET
    }

    fun getFirstPartURL(imageType: ImageType): String {
        return when (imageType) {
            ImageType.MOVIE_POSTER ->
                getBaseUrlImage() + getSecondUrlImage(ImageType.MOVIE_BACKDROP)
            ImageType.MOVIE_BACKDROP ->
                getBaseUrlImage() + getSecondUrlImage(ImageType.MOVIE_BACKDROP)
            ImageType.ACTOR_PHOTO ->
                getBaseUrlImage() + getSecondUrlImage(ImageType.ACTOR_PHOTO)
        }
    }

    private fun getBaseUrlImage(): String {
        return BASE_URL_IMAGE
    }

    private fun getSecondUrlImage(imageType: ImageType): String {
        return when (imageType) {
            ImageType.MOVIE_POSTER -> posterSizeList[POSTER_SIZE_MEDIUM]
            ImageType.MOVIE_BACKDROP -> backdropSizeList[BACKDROP_SIZE_SMALL]
            ImageType.ACTOR_PHOTO -> actorPhotoSizeList[ACTOR_PHOTO_SIZE_SMALL]
        }

    }

    //https://developers.themoviedb.org/3/configuration/get-api-configuration
    private const val BASE_URL_GET = "https://api.themoviedb.org/3/"
    private const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/"

    private val posterSizeList = arrayOf(
        "w92",
        "w154",
        "w185",
        "w300",
        "w500",
        "w780",
        "original"
    )
    private val backdropSizeList = arrayOf(
        "w500",
        "w780",
        "w1200",
        "original"
    )
    private val actorPhotoSizeList = arrayOf(
        "w45",
        "w185",
        "h632",
        "original"
    )
    private const val POSTER_SIZE_MEDIUM = 4
    private const val BACKDROP_SIZE_SMALL = 0
    private const val ACTOR_PHOTO_SIZE_SMALL = 1

    enum class ImageType {
        MOVIE_POSTER,
        MOVIE_BACKDROP,
        ACTOR_PHOTO
    }
}