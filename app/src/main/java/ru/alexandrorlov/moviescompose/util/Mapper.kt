package ru.alexandrorlov.moviescompose.util

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import ru.alexandrorlov.moviescompose.config.Network
import ru.alexandrorlov.moviescompose.model.Movie
import ru.alexandrorlov.moviescompose.network.MoviePopularNetwork

class Mapper {

    private fun moviePopularNetworkMapMovie (moviePopularNetwork: MoviePopularNetwork): Movie {
        return Movie(
            id = moviePopularNetwork.id,
            name = moviePopularNetwork.name,
            photo = (
                    Network.getTopSecretURL()
                            + moviePopularNetwork.posterPath
                    ),
            dateRelease = moviePopularNetwork.dateRelease,
            rating = convertRatingForFiveStar(moviePopularNetwork.rating),
            ageRating = "12+",//TODO убрать хардкор. рейтинг сложным запросом получается
            description = moviePopularNetwork.description
        )
    }

    fun moviePopularNetworkListMapMovieList (moviePopularNetworkList: List<MoviePopularNetwork>):
            SnapshotStateList<Movie> {
        val movies = mutableStateListOf<Movie>()
        moviePopularNetworkList.forEach {
            movies.add(
                moviePopularNetworkMapMovie(it)
            )
        }
        return movies
    }

    private fun convertRatingForFiveStar(rating: Double): Int {
        return (rating * 0.5).toInt()
    }
}