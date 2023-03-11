package ru.alexandrorlov.moviescompose.data

import android.util.Log
import ru.alexandrorlov.moviescompose.data.local.RepositoryLocal
import ru.alexandrorlov.moviescompose.data.remote.RepositoryRemote
import ru.alexandrorlov.moviescompose.model.ui.Movie
import ru.alexandrorlov.moviescompose.data.remote.Result
import ru.alexandrorlov.moviescompose.model.ui.MovieDetail

class Repository {
    private val repositoryLocal = RepositoryLocal()
    private val repositoryRemote = RepositoryRemote

    suspend fun getResultListMovie(): Result<Any>{
        var movieList: List<Movie> = repositoryLocal.getAllMovie()
        return if (movieList.isEmpty()) {
            val resultRemote = repositoryRemote.getResultListMovieFromNetwork()
            if (resultRemote is Result.Success) {
                movieList = resultRemote.data as List<Movie>
                repositoryLocal.deleteAll()
                repositoryLocal.insertAll(movieList)
                Result.Success(movieList)
            } else {
                resultRemote
            }
        } else {
            Result.Success(movieList)
        }
    }

    suspend fun getResultMovieDetails(id: Int): Result<Any> {
        var movieDetail = repositoryLocal.getMovieDetail(id)

        return if (movieDetail.dateRelease.isEmpty()) {
            val resultRemote = repositoryRemote.getResultMovieDetailsNetwork(id)
            if (resultRemote is Result.Success) {
                movieDetail = resultRemote.data as MovieDetail
                repositoryLocal.updateMovieDetail(movieDetail)
                Result.Success(movieDetail)
            } else {
                resultRemote
            }
        } else {
            Result.Success(movieDetail)
        }
    }
}