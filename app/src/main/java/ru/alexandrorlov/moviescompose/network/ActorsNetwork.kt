package ru.alexandrorlov.moviescompose.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorsNetwork(
    @SerialName("cast")
    val actors: List<ActorNetwork>
)

@Serializable
data class ActorNetwork(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("profile_path")
    val photoPath: String? = null
)