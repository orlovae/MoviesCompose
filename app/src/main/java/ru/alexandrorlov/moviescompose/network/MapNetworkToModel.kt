package ru.alexandrorlov.moviescompose.network

import ru.alexandrorlov.moviescompose.config.AppConfig
import ru.alexandrorlov.moviescompose.config.NetworkConfig.ImageType
import ru.alexandrorlov.moviescompose.config.NetworkConfig
import ru.alexandrorlov.moviescompose.model.Actor
import ru.alexandrorlov.moviescompose.model.Genre
import ru.alexandrorlov.moviescompose.model.Movie

object MapNetworkToModel {
    fun ResultFromNetworkMoviePopularList.mapToMovie(): List<Movie> {
        return moviePopularNetworkList.map { moviePopularNetwork ->
            Movie(
                id = moviePopularNetwork.id,
                title = moviePopularNetwork.title,
                poster = buildUrlImage(
                    ImageType.MOVIE_POSTER,
                    moviePopularNetwork.posterPath
                ),
                rating = mapRatingToInt(
                    moviePopularNetwork.rating
                ),
                description = moviePopularNetwork.description
            )
        }
    }
    fun mapGenreNetworkListToModelList(genresNetwork: List<GenreNetwork>): List<Genre> {
        return genresNetwork.map { genreNetwork ->
            Genre(
                id = genreNetwork.id,
                name = genreNetwork.name
            )
        }
    }

    fun ResultActorNetworkList.mapToActor(): List<Actor> {
        return actorNetworkList.map { actorNetwork ->
            Actor(
                id = actorNetwork.id,
                name = actorNetwork.name,
                photo = buildUrlImage(ImageType.ACTOR_PHOTO, actorNetwork.photoPath)
            )
        }
    }

    //TODO В данной функции можно добавить агрумент language: String для выбора нащиональной системы рейтинга
    fun getAgeRating(movieDetailsNetwork: MovieDetailsNetwork): String {
        val result = movieDetailsNetwork.releaseDates.results

        result.forEach {
            if (it.iso_3166_1.equals(
                    NetworkConfig.LANGUAGE_FOR_PG,
                    ignoreCase = true
                )
            ) {
                return it.release_dates_result_item[0].certification.toString()
            }
        }
        return NetworkConfig.ERROR_PG
    }

    fun buildUrlImage(imageType: ImageType, path: String?): String {
        return when (imageType) {
            ImageType.MOVIE_POSTER ->
                NetworkConfig.getFirstPartURL(ImageType.MOVIE_POSTER) + path
            ImageType.MOVIE_BACKDROP ->
                NetworkConfig.getFirstPartURL(ImageType.MOVIE_BACKDROP) + path
            ImageType.ACTOR_PHOTO ->
                NetworkConfig.getFirstPartURL(ImageType.ACTOR_PHOTO)+ path
        }
    }

    fun mapRatingToInt(rating: Double): Int {
        return (rating * 0.5).toInt()
    }
}