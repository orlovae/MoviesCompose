package ru.alexandrorlov.moviescompose.data.local

import android.util.Log
import ru.alexandrorlov.moviescompose.App
import ru.alexandrorlov.moviescompose.model.ui.Movie
import ru.alexandrorlov.moviescompose.model.ui.MovieDetail

class RepositoryLocal() {
    private val dB = LocalDataBase.instance
    private val mapperDbToUi = MapperDbToUi()
    private val mapperUiToDb = MapperUiToDb()

    suspend fun getAllMovie(): List<Movie> {
        Log.d("OAE", "getAllMovie start")


        val list = mapperDbToUi.mapToMovieUiList(
            dB.MovieDao().getAll()
        )
        Log.d("OAE", "getAllMovie end")
        return list
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