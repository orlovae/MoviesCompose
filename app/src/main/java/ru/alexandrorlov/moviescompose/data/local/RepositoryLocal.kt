package ru.alexandrorlov.moviescompose.data.local

import ru.alexandrorlov.moviescompose.model.ui.Movie
import ru.alexandrorlov.moviescompose.model.ui.MovieDetail

class RepositoryLocal() {
    private val dB = LocalDataBase.instance
    private val mapperDbToUi = MapperDbToUi()
    private val mapperUiToDb = MapperUiToDb()

    suspend fun getAllMovie(): List<Movie> {
        return mapperDbToUi.mapToMovieUiList(
            dB.MovieDao().getAll()
        )
    }

    suspend fun getAllMovieDetail(): List<MovieDetail> {
        return mapperDbToUi.mapToMovieDetailUiList(
            dB.MovieDao().getAll()
        )
    }

    suspend fun getMovieDetail(id: Int): MovieDetail {
        return mapperDbToUi.mapToMovieDetailUi(
        dB.MovieDao().getById(id)
        )
    }

    suspend fun insertAll(movieList: List<Movie>) {
        dB.MovieDao().insertAll(
            mapperUiToDb.mapMovieListToMovieDbList(movieList)
        )
    }

    suspend fun updateMovieDetail(movieDetail: MovieDetail) {
        dB.MovieDao().update(
            mapperUiToDb.mapToMovieDb(movieDetail)
        )
    }

    suspend fun deleteAll() {
        dB.MovieDao().deleteAll()
    }
}