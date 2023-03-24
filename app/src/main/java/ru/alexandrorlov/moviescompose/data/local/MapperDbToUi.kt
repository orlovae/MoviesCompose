package ru.alexandrorlov.moviescompose.data.local

import ru.alexandrorlov.moviescompose.di.AppScope
import ru.alexandrorlov.moviescompose.model.entity.GenreDb
import ru.alexandrorlov.moviescompose.model.entity.MovieDb
import ru.alexandrorlov.moviescompose.model.ui.Actor
import ru.alexandrorlov.moviescompose.model.ui.Genre
import ru.alexandrorlov.moviescompose.model.ui.Movie
import ru.alexandrorlov.moviescompose.model.ui.MovieDetail
import javax.inject.Inject

@AppScope
class MapperDbToUi @Inject constructor() {
    fun MovieDb.mapToMovieUi(): Movie {
        return Movie(
            id = id,
            title = title,
            poster = poster,
            rating = rating,
            ageRating = ageRating,
            description = description
        )
    }

    fun mapToMovieUiList(list: List<MovieDb>): List<Movie> {
        return list.map { movieDb ->
            movieDb.mapToMovieUi()
        }
    }

    fun mapToMovieDetailUi(movieDb: MovieDb): MovieDetail {
        return MovieDetail(
            id = movieDb.id,
            title = movieDb.title,
            poster = movieDb.poster,
            backdrop = movieDb.backdrop,
            dateRelease = movieDb.dateRelease,
            rating = movieDb.rating,
            ageRating = movieDb.ageRating,
            description = movieDb.description,
            genreList = getGenreList(movieDb),
            actorList = getActorList(movieDb)
        )
    }

    private fun getGenreList(movieDb: MovieDb): List<Genre> {
        val genreList: MutableList<Genre> = mutableListOf()
        val genreIdList = movieDb.genreId
        val genreNameList = movieDb.genreName
        if (genreIdList.size == genreNameList.size) {
            for (index in genreIdList.indices) {
                genreList.add(
                    Genre(
                        id = genreIdList[index],
                        name = genreNameList[index]
                    )
                )
            }
        }
        return genreList.toList()
    }

    private fun getActorList(movieDb: MovieDb): List<Actor> {
        val actorList: MutableList<Actor> = mutableListOf()
        val actorIdList = movieDb.actorId
        val actorNameList = movieDb.actorName
        val actorPhotoList = movieDb.actorPhoto

        if (actorIdList.size == actorNameList.size &&
            actorNameList.size == actorPhotoList.size
        ) {
            for (index in actorIdList.indices) {
                actorList.add(
                    Actor(
                        id = actorIdList[index],
                        name = actorNameList[index],
                        photo = actorPhotoList[index]
                    )
                )
            }
        }
        return actorList.toList()
    }

    fun mapToMovieDetailUiList(list: List<MovieDb>): List<MovieDetail> {
        return list.map { movieDb ->
            mapToMovieDetailUi(movieDb)
        }
    }

    fun GenreDb.mapToGenreUi(): Genre {
        return Genre(
            id = id,
            name = name
        )
    }

    fun List<Genre>.mapToGenreUiList(list: List<GenreDb>): List<Genre> {
        return list.map { genreDb ->
            genreDb.mapToGenreUi()
        }
    }
}