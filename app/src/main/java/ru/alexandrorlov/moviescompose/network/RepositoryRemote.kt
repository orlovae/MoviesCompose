package ru.alexandrorlov.moviescompose.network

import ru.alexandrorlov.moviescompose.config.NetworkConfig.ImageType
import ru.alexandrorlov.moviescompose.model.Actor
import ru.alexandrorlov.moviescompose.model.MovieDetail
import ru.alexandrorlov.moviescompose.model.Movie
import ru.alexandrorlov.moviescompose.network.MapNetworkToModel.mapToActor
import ru.alexandrorlov.moviescompose.network.MapNetworkToModel.mapToMovie

object RepositoryRemote {
    private val remoteDataSource: TMDBRemoteDataSource = TMDBRemoteDataSource.Singleton.instance

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

        val resultMovieDetailsNetwork = remoteDataSource.getResultMovieDetailNetwork(id)

        if (resultMovieDetailsNetwork is Result.Success) {
            val movieDetailsNetwork: MovieDetailsNetwork =
                resultMovieDetailsNetwork.data as MovieDetailsNetwork

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
                    rating = MapNetworkToModel
                        .mapRatingToInt(
                            movieDetailsNetwork.rating
                        ),
                    ageRating = MapNetworkToModel
                        .getAgeRating(
                            movieDetailsNetwork
                        ),
                    description = movieDetailsNetwork.overview,
                    genreList = MapNetworkToModel
                        .mapGenreNetworkListToModelList(
                        movieDetailsNetwork.genreNetworkList
                    ),
                    actorList = actorList
                )

        } else {
            return resultMovieDetailsNetwork
        }
        return Result.Success(movieDetail)
    }

    private suspend fun getActorList(id: Int) : List<Actor> {
        val actorList: List <Actor>

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