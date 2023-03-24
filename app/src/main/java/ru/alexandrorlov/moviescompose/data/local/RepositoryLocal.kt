package ru.alexandrorlov.moviescompose.data.local

import ru.alexandrorlov.moviescompose.di.AppScope
import ru.alexandrorlov.moviescompose.model.ui.Movie
import ru.alexandrorlov.moviescompose.model.ui.MovieDetail
import javax.inject.Inject

@AppScope
class RepositoryLocal @Inject constructor(
    private val db: LocalDataBase,
    private val mapperDbToUi: MapperDbToUi,
    private val mapperUiToDb: MapperUiToDb
) {

    suspend fun getAllMovie(): List<Movie> {
        return mapperDbToUi.mapToMovieUiList(
            db.MovieDao().getAll()
        )
    }

    suspend fun getAllMovieDetail(): List<MovieDetail> {
        return mapperDbToUi.mapToMovieDetailUiList(
            db.MovieDao().getAll()
        )
    }

    suspend fun getMovieDetail(id: Int): MovieDetail {
        return mapperDbToUi.mapToMovieDetailUi(
            db.MovieDao().getById(id)
        )
    }

    suspend fun insertAll(movieList: List<Movie>) {
        db.MovieDao().insertAll(
            mapperUiToDb.mapMovieListToMovieDbList(movieList)
        )
    }

    suspend fun updateMovieDetail(movieDetail: MovieDetail) {
        db.MovieDao().update(
            mapperUiToDb.mapToMovieDb(movieDetail)
        )
    }

    suspend fun deleteAll() {
        db.MovieDao().deleteAll()
    }
}