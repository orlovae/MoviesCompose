package ru.alexandrorlov.moviescompose

import android.app.Application
import android.content.Context
import kotlinx.coroutines.flow.flow
import ru.alexandrorlov.moviescompose.network.MoviePopularNetwork
import ru.alexandrorlov.moviescompose.util.AssetsReader
import ru.alexandrorlov.moviescompose.util.Mapper

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        listMoviePopularNetwork = AssetsReader.getMoviesPopular()
    }

    companion object {
        lateinit var appContext: Context
        lateinit var listMoviePopularNetwork: List<MoviePopularNetwork>
        private val mapper = Mapper()

        val movieListFlow = flow {
            val moviesList = mapper.moviePopularNetworkListMapMovieList(listMoviePopularNetwork)
            emit(moviesList)
        }

        fun getMovie(id: Int?) = flow {
            val moviesList = mapper.moviePopularNetworkListMapMovieList(listMoviePopularNetwork)
            emit(
            moviesList
                .firstOrNull { movie -> movie.id == id }
            )
        }
    }
}