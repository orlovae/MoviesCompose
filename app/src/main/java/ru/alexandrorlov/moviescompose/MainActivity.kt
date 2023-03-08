package ru.alexandrorlov.moviescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.alexandrorlov.moviescompose.screen.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val navController: NavHostController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "movieList") {
                    composable(
                        route = "movieList",
                        arguments = listOf(navArgument("id") {type = NavType.IntType})
                    ) {
                        ScreenMovieList { id ->
                            navController.navigate("movie/$id")
                        }
                    }
                    composable(
                        route = "movie/{id}"
                    )
                    {
                        ScreenMovieDetail()
                    }
                }
            }
        }
    }
}