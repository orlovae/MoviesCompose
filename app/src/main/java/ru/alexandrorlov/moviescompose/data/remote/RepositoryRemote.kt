package ru.alexandrorlov.moviescompose.data.remote

import ru.alexandrorlov.moviescompose.config.DataRemoteConfig.ImageType
import ru.alexandrorlov.moviescompose.data.remote.MapNetworkToModel.mapToActor
import ru.alexandrorlov.moviescompose.data.remote.MapNetworkToModel.mapToMovie
import ru.alexandrorlov.moviescompose.di.AppScope
import ru.alexandrorlov.moviescompose.model.remote.MovieDetailsNetwork
import ru.alexandrorlov.moviescompose.model.remote.ResultActorNetworkList
import ru.alexandrorlov.moviescompose.model.remote.ResultFromNetworkMoviePopularList
import ru.alexandrorlov.moviescompose.model.ui.Actor
import ru.alexandrorlov.moviescompose.model.ui.Movie
import ru.alexandrorlov.moviescompose.model.ui.MovieDetail
import javax.inject.Inject

@AppScope
class RepositoryRemote @Inject constructor(private val remoteDataSource: TMDBRemoteDataSource) {

    suspend fun getResultListMovieFromNetwork(): Result<Any> {
        val movieList: List<Movie>

        val response = remoteDataSource.getResultFromNetworkMoviePopularList()

        if (response is Result.Success) {
            val resultFromNetworkMoviePopularList: ResultFromNetworkMoviePopularList =
                response.data as ResultFromNetworkMoviePopularList

            movieList = resultFromNetworkMoviePopularList.mapToMovie()
        } else {
            return response
        }
        return Result.Success(movieList)
    }

    suspend fun getResultMovieDetailsNetwork(id: Int): Result<Any> {
        val movieDetail: MovieDetail

        val resultMovieDetailNetwork = remoteDataSource.getResultMovieDetailNetwork(id)

        if (resultMovieDetailNetwork is Result.Success) {
            val movieDetailsNetwork: MovieDetailsNetwork =
                resultMovieDetailNetwork.data as MovieDetailsNetwork

            val actorList: List<Actor> = getActorList(movieDetailsNetwork.id)

            movieDetail = MovieDetail(
                id = id,
                title = movieDetailsNetwork.title,
                poster = MapNetworkToModel.buildUrlImage(
                    ImageType.MOVIE_POSTER,
                    movieDetailsNetwork.poster
                ),
                backdrop = MapNetworkToModel.buildUrlImage(
                    ImageType.MOVIE_BACKDROP,
                    movieDetailsNetwork.backdrop
                ),
                dateRelease = movieDetailsNetwork.releaseDate,
                rating = MapNetworkToModel.mapRatingToInt(
                    movieDetailsNetwork.rating
                ),
                ageRating = MapNetworkToModel.getAgeRating(
                    movieDetailsNetwork
                ),
                description = movieDetailsNetwork.overview,
                genreList = MapNetworkToModel.mapGenreNetworkListToModelList(
                    movieDetailsNetwork.genreNetworkList
                ),
                actorList = actorList
            )

        } else {
            return resultMovieDetailNetwork
        }
        return Result.Success(movieDetail)
    }

    private suspend fun getActorList(id: Int): List<Actor> {
        val actorList: List<Actor>

        val resultActorsNetwork = remoteDataSource.getResultListActorsNetwork(id)

        if (resultActorsNetwork is Result.Success) {
            val resultActorNetworkList: ResultActorNetworkList =
                resultActorsNetwork.data as ResultActorNetworkList

            actorList = resultActorNetworkList.mapToActor()

        } else {
            return emptyList()
        }
        return actorList
    }
}