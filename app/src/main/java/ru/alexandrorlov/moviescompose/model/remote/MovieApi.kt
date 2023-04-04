package ru.alexandrorlov.moviescompose.model.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultFromNetworkMoviePopularList(
    @SerialName("results")
    val moviePopularNetworkList: List<MoviePopularNetwork>,
)

@Serializable
data class MoviePopularNetwork(
    @SerialName("poster_path")
    val posterPath: String?,

    @SerialName("genre_ids")
    val genres: List<Int>,

    @SerialName("id")
    val id: Int,

    @SerialName("overview")
    val description: String,

//    @SerialName("release_date")
//    val dateRelease: String,
//TODO Почему-то при включении данного поля, приложение падает.

    @SerialName("title")
    val title: String,

    @SerialName("vote_average")
    val rating: Double
)