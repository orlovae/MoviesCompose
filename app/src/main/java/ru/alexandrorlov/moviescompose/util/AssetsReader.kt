package ru.alexandrorlov.moviescompose.util

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.alexandrorlov.moviescompose.App
import ru.alexandrorlov.moviescompose.config.AppConfig
import ru.alexandrorlov.moviescompose.network.MoviePopularNetwork
import ru.alexandrorlov.moviescompose.network.MoviesPopularNetwork

object AssetsReader {
    private val propertyJson = Json {
        ignoreUnknownKeys = true
    }

    private val stringFromJson = App.appContext
        .assets
        .open(AppConfig.ASSETS_FILE_DATA)
        .bufferedReader()
        .use {
            it.readText()
        }

    fun getMoviesPopular(): List<MoviePopularNetwork> {
        return propertyJson
            .decodeFromString<MoviesPopularNetwork>(
                stringFromJson
            )
            .moviesPopular
    }
}