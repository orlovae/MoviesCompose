package ru.alexandrorlov.moviescompose.model.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class `Languages`(
    @SerialName("iso_639_1")
    val language: String,

    @SerialName("english_name")
    val englishName: String,

    @SerialName("name")
    val originalName: String,
)