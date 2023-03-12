package ru.alexandrorlov.moviescompose.model.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultActorNetworkList(
    @SerialName("cast")
    val actorNetworkList: List<ActorNetwork>
)

@Serializable
data class ActorNetwork(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("profile_path")
    val photoPath: String?
)