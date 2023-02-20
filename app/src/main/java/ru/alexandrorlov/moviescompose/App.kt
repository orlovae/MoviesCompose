package ru.alexandrorlov.moviescompose

import android.app.Application
import android.content.Context
import ru.alexandrorlov.moviescompose.model.Movie
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

        fun getListMovie() : List<Movie> {
            return mapper.moviePopularNetworkListMapMovieList(listMoviePopularNetwork)
        }
        fun getMovie(id: Int): Movie {
            val listMovie = mapper.moviePopularNetworkListMapMovieList(listMoviePopularNetwork)
            return (listMovie.filter { it.id == id }).first()
        }
    }
}