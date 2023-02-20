package ru.alexandrorlov.moviescompose.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.alexandrorlov.moviescompose.model.Movie

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "movieList"
) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = startDestination) {
        composable(
            "movieList"
        ) {
            MoviesScreen (
                movies = MoviesViewModel().movies,
                onNavigateToMovieDetail = navController
            )
        }
        composable(
            route = "movie/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType }
            )
        ) {
            val movieId = it.arguments?.getInt("id")
            MovieDetailScreen(
                movie = MovieDetailViewModel().onPressItemMovieScreen(movieId)
            )
        }
    }
}