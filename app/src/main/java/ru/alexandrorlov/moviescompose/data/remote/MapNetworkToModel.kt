package ru.alexandrorlov.moviescompose.data.remote

import ru.alexandrorlov.moviescompose.config.DataRemoteConfig
import ru.alexandrorlov.moviescompose.config.DataRemoteConfig.ImageType
import ru.alexandrorlov.moviescompose.model.remote.*
import ru.alexandrorlov.moviescompose.model.ui.Actor
import ru.alexandrorlov.moviescompose.model.ui.Genre
import ru.alexandrorlov.moviescompose.model.ui.Movie

object MapNetworkToModel {
    fun ResultFromNetworkMoviePopularList.mapToMovie(): List<Movie> {
        return moviePopularNetworkList.map { movieRemote ->
            Movie(
                id = movieRemote.id,
                title = movieRemote.title,
                poster = buildUrlImage(
                    ImageType.MOVIE_POSTER,
                    movieRemote.posterPath
                ),
                rating = mapRatingToInt(
                    movieRemote.rating
                ),
                description = movieRemote.description,
                dateRelease = movieRemote.dateRelease,
                genreList = movieRemote.genres
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

    fun ResultGenreNetworkList.mapToGenre(): List<Genre> {
        return genreNetworkList.map { genreNetwork ->
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
                    DataRemoteConfig.LANGUAGE_FOR_PG,
                    ignoreCase = true
                )
            ) {
                return it.release_dates_result_item[0].certification.toString()
            }
        }
        return DataRemoteConfig.ERROR_PG
    }

    fun buildUrlImage(imageType: ImageType, path: String?): String {
        return when (imageType) {
            ImageType.MOVIE_POSTER ->
                DataRemoteConfig.getFirstPartURL(ImageType.MOVIE_POSTER) + path
            ImageType.MOVIE_BACKDROP ->
                DataRemoteConfig.getFirstPartURL(ImageType.MOVIE_BACKDROP) + path
            ImageType.ACTOR_PHOTO ->
                DataRemoteConfig.getFirstPartURL(ImageType.ACTOR_PHOTO) + path
        }
    }

    fun mapRatingToInt(rating: Double): Int {
        return (rating * 0.5).toInt()
    }
}