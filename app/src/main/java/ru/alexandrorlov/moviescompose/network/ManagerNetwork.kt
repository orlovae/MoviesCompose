package ru.alexandrorlov.moviescompose.network

import ru.alexandrorlov.moviescompose.config.NetworkConfig.ImageType
import ru.alexandrorlov.moviescompose.model.MovieDetail
import ru.alexandrorlov.moviescompose.model.Movie

object ManagerNetwork {
    private val repositoryNetwork: RepositoryNetwork = RepositoryNetwork.Singleton.instance

    suspend fun getResultListMovieFromNetwork(): Result<Any> {
        var movieList: List<Movie> = listOf()

        val resultListMoviePopular = repositoryNetwork.getResultListMoviePopularNetwork()

        if (resultListMoviePopular is Result.Success) {
            val movieListPopularNetwork: MoviesPopularNetwork =
                resultListMoviePopular.data as MoviesPopularNetwork
            val moviesPopular: List<MoviePopularNetwork> = movieListPopularNetwork.moviesPopular

            movieList = MapNetworkToModel.mapMovieListPopularToMovieList(moviesPopular)
        }

        if (resultListMoviePopular is Result.Error) {
            return resultListMoviePopular
        }

        return Result.Success(movieList)
    }

    suspend fun getResultMovieDetailsNetwork(id: Int): Result<Any> {
        var movieDetail = MovieDetail()

        val resultMovieDetailsNetwork = repositoryNetwork.getResultMovieDetailNetwork(id)

        if (resultMovieDetailsNetwork is Result.Success) {
            val movieDetailsNetwork: MovieDetailsNetwork =
                resultMovieDetailsNetwork.data as MovieDetailsNetwork

            val resultActorsNetwork = repositoryNetwork.getResultListActorsNetwork(id)

            if (resultActorsNetwork is Result.Success) {
                val actorsNetwork: ActorsNetwork =
                    resultActorsNetwork.data as ActorsNetwork

                val listActorNetwork: List<ActorNetwork> = actorsNetwork.actors

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
                    actorList = MapNetworkToModel
                        .getReduceActorList(
                            MapNetworkToModel.mapListActorNetworkToListModel(
                                listActorNetwork
                            )
                        )
                )
            }
            if (resultActorsNetwork is Result.Error) {
                return resultActorsNetwork
            }
        }

        if (resultMovieDetailsNetwork is Result.Error) {
            return resultMovieDetailsNetwork
        }
        return Result.Success(movieDetail)
    }
}