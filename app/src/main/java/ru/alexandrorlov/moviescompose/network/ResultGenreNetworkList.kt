package ru.alexandrorlov.moviescompose.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultGenreNetworkList(
    @SerialName("genres")
    val genreNetworkList: List<GenreNetwork>
)

@Serializable
data class GenreNetwork(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String
)