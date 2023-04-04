package ru.alexandrorlov.moviescompose.data

import ru.alexandrorlov.moviescompose.config.DataLocalConfig.ERROR_ID
import ru.alexandrorlov.moviescompose.config.ModelConfig
import ru.alexandrorlov.moviescompose.data.local.RepositoryLocal
import ru.alexandrorlov.moviescompose.data.local.RepositoryLocalGenre
import ru.alexandrorlov.moviescompose.data.remote.RepositoryRemote
import ru.alexandrorlov.moviescompose.data.remote.Result
import ru.alexandrorlov.moviescompose.di.AppScope
import ru.alexandrorlov.moviescompose.model.ui.Genre
import ru.alexandrorlov.moviescompose.model.ui.Movie
import ru.alexandrorlov.moviescompose.model.ui.MovieDetail
import javax.inject.Inject

@AppScope
class Repository @Inject constructor(
    private val repositoryLocalGenre: RepositoryLocalGenre,
    private val repositoryLocal: RepositoryLocal,
    private val repositoryRemote: RepositoryRemote
) {

    suspend fun getResultMovieList(): Result<Any> {
        val movieList: List<Movie> = repositoryLocal.getAllMovie()
        return if (movieList.isEmpty()) {
            val result = fetchResultMovieList()
            if (result is Result.Success) {
                Result.Success(result.data)
            } else {
                result
            }
        } else {
            Result.Success(movieList)
        }
    }

    suspend fun fetchResultMovieList(): Result<Any> {
        val resultRemote = repositoryRemote.getResultListMovieFromNetwork()
        return if (resultRemote is Result.Success) {
            val movieList = resultRemote.data as List<Movie>
            repositoryLocal.deleteAll()
            repositoryLocal.insertAll(movieList)
            Result.Success(movieList)
        } else {
            resultRemote
        }
    }

    suspend fun getResultMovieDetail(id: Int): Result<Any> {
        var movieDetail = repositoryLocal.getMovieDetail(id)

        return if (movieDetail.actorList.isEmpty()) {
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

    suspend fun getResultGenreList(): Result<Any> {
        val genreList: List<Genre> = repositoryLocalGenre.getAllGenre()
        return if (genreList.isEmpty()) {
            val result = fetchResultGenreList()
            if (result is Result.Success) {
                Result.Success(result.data)
            } else {
                result
            }
        } else {
            Result.Success(genreList)
        }
    }

    suspend fun fetchResultGenreList(): Result<Any> {
        val resultRemote = repositoryRemote.getResultGenreNetworkList()
        return if (resultRemote is Result.Success) {
            val genreList = resultRemote.data as List<Genre>
            repositoryLocalGenre.deleteAll()
            repositoryLocalGenre.insertAll(genreList)
            Result.Success(genreList)
        } else {
            resultRemote
        }
    }

    suspend fun getResultGenre(id: Int): Result<Any> {
        val genre = repositoryLocalGenre.getGenre(id)

        return if (genre.id != ModelConfig.ERROR_INT) {
            Result.Success(genre)
        } else {
            Result.Error(ERROR_ID + "$id")
        }
    }
}