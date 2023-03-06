package ru.alexandrorlov.moviescompose.network

import ru.alexandrorlov.moviescompose.config.NetworkConfig.ImageType
import ru.alexandrorlov.moviescompose.model.Actor
import ru.alexandrorlov.moviescompose.model.MovieDetail
import ru.alexandrorlov.moviescompose.model.Movie

object RepositoryNetwork {
    private val networkClient: NetworkClientNetwork = NetworkClientNetwork.Singleton.instance

    suspend fun getResultListMovieFromNetwork(): Result<Any> {
        val movieList: List<Movie>

        val resultListMoviePopular = networkClient.getResultListMoviePopularNetwork()

        if (resultListMoviePopular is Result.Success) {
            val movieListPopularNetwork: MoviesPopularNetwork =
                resultListMoviePopular.data as MoviesPopularNetwork
            val moviesPopular: List<MoviePopularNetwork> = movieListPopularNetwork.moviesPopular

            movieList = MapNetworkToModel.mapMovieListPopularToMovieList(moviesPopular)
        } else {
            return resultListMoviePopular
        }
        return Result.Success(movieList)
    }

    suspend fun getResultMovieDetailsNetwork(id: Int): Result<Any> {
        val movieDetail: MovieDetail

        val resultMovieDetailsNetwork = networkClient.getResultMovieDetailNetwork(id)

        if (resultMovieDetailsNetwork is Result.Success) {
            val movieDetailsNetwork: MovieDetailsNetwork =
                resultMovieDetailsNetwork.data as MovieDetailsNetwork

            val actorList: List<Actor> = getActorList(movieDetailsNetwork)

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
                        .mapGenresFromNetworkToModel(
                            movieDetailsNetwork.genres
                        ),
                    actorList = actorList
                )

        } else {
            return resultMovieDetailsNetwork
        }
        return Result.Success(movieDetail)
    }

    private suspend fun getActorList(movieDetailsNetwork: MovieDetailsNetwork) : List<Actor> {
        val actorList: List <Actor>

        val resultActorsNetwork = networkClient.getResultListActorsNetwork(movieDetailsNetwork.id)

        if (resultActorsNetwork is Result.Success) {
            val actorsNetwork: ActorsNetwork =
                resultActorsNetwork.data as ActorsNetwork

            val actorNetworkList: List<ActorNetwork> = actorsNetwork.actors

            actorList = MapNetworkToModel.getReduceActorList(
                    MapNetworkToModel.mapActorNetworkListToModelList(
                        actorNetworkList
                    )
                )

        } else {
            return emptyList()
        }
        return actorList
    }
}