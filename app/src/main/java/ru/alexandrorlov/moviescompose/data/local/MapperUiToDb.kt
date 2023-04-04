package ru.alexandrorlov.moviescompose.data.local

import ru.alexandrorlov.moviescompose.di.AppScope
import ru.alexandrorlov.moviescompose.model.entity.GenreDb
import ru.alexandrorlov.moviescompose.model.entity.MovieDb
import ru.alexandrorlov.moviescompose.model.ui.Genre
import ru.alexandrorlov.moviescompose.model.ui.Movie
import ru.alexandrorlov.moviescompose.model.ui.MovieDetail
import javax.inject.Inject

@AppScope
class MapperUiToDb @Inject constructor() {
    fun Movie.mapToMovieDb(): MovieDb {
        return MovieDb(
            id = id,
            title = title,
            poster = poster,
            rating = rating,
            ageRating = ageRating,
            description = description,
            dateRelease = dateRelease,
            genreId = genreList
        )
    }

    fun mapMovieListToMovieDbList(list: List<Movie>): List<MovieDb> {
        return list.map { movie ->
            movie.mapToMovieDb()
        }
    }

    fun mapToMovieDb(movieDetail: MovieDetail): MovieDb {
        return MovieDb(
            id = movieDetail.id,
            title = movieDetail.title,
            poster = movieDetail.poster,
            backdrop = movieDetail.backdrop,
            dateRelease = movieDetail.dateRelease,
            rating = movieDetail.rating,
            ageRating = movieDetail.ageRating,
            description = movieDetail.description,
            genreId = movieDetail.genreList.map { genre -> genre.id },
            genreName = movieDetail.genreList.map { genre -> genre.name },
            actorId = movieDetail.actorList.map { actor -> actor.id },
            actorName = movieDetail.actorList.map { actor -> actor.name },
            actorPhoto = movieDetail.actorList.map { actor -> actor.photo }
        )
    }

    fun mapMovieDetailListToMovieDbList(list: List<MovieDetail>): List<MovieDb> {
        return list.map { movieDetail ->
            mapToMovieDb(movieDetail)
        }
    }

    fun mapToGenreDb(genre: Genre): GenreDb {
        return GenreDb(
            id = genre.id,
            name = genre.name
        )
    }

    fun mapGenreListToGenreDbList(list: List<Genre>): List<GenreDb> {
        return list.map { genre ->
            mapToGenreDb(genre)
        }
    }
}