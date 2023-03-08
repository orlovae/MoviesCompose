package ru.alexandrorlov.moviescompose.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsNetwork(
    @SerialName("id")
    val id: Int,

    @SerialName("title")
    val title: String,

    @SerialName("overview")
    val overview: String,

    @SerialName("poster_path")
    val poster: String?,

    @SerialName("backdrop_path")
    val backdrop: String?,

    @SerialName("vote_average")
    val rating: Double,

    @SerialName("vote_count")
    val reviews: Int,

    @SerialName("runtime")
    val runtime: Int?,

    @SerialName("release_date")
    val releaseDate: String,

    @SerialName("genres")
    val genreNetworkList: List<GenreNetwork>,

    @SerialName("release_dates")
    val releaseDates: ReleaseDates
)

@Serializable
data class ReleaseDates(
    @SerialName("results")
    val results: List<ResultItem>
)

@Serializable
data class ResultItem(
    @SerialName("iso_3166_1")
    val iso_3166_1: String,

    @SerialName("release_dates")
    val release_dates_result_item: List<ReleaseDate>
)

@Serializable
data class ReleaseDate(
    @SerialName("certification")
    val certification: String?
)