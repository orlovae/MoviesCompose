package ru.alexandrorlov.moviescompose.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

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
                MoviesViewModel(),
                onNavigateToMovieDetail = navController
            )
        }
        composable(
            route = "movie/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType }
            )
        ) {
            val movieId = it.arguments?.getInt("id")
            val movieViewModel: MovieDetailViewModel = viewModel()
            MovieDetailScreen(
                movieViewModel,
                movieId
            )
        }
    }
}