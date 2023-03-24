package ru.alexandrorlov.moviescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.alexandrorlov.moviescompose.config.UIConfig.ID
import ru.alexandrorlov.moviescompose.config.UIConfig.MOVIE_LIST
import ru.alexandrorlov.moviescompose.screen.*
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelMovieListFactory: ViewModelMovieListFactory

    @Inject
    lateinit var viewModelMovieDetailAssistedFactory: ViewModelMovieDetailAssistedFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainComponent = App.appComponent.mainComponent().create()
        mainComponent.injectMainActivity(this)

        val viewModelMovieList =
            ViewModelProvider(this, viewModelMovieListFactory)
                .get(ViewModelMovieList::class.java)

        setContent {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val navController: NavHostController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = MOVIE_LIST
                ) {
                    composable(
                        route = MOVIE_LIST
                    ) {
                        ScreenMovieList(
                            viewModel = viewModelMovieList
                        ) { id ->
                            navController.navigate("movie/$id")
                        }
                    }
                    composable(
                        route = "movie/{id}",
                        arguments = listOf(navArgument(ID) { type = NavType.IntType })
                    )
                    {
                        val id = it.arguments?.getInt(ID)
                        ScreenMovieDetail(
                            viewModel = viewModel(
                                factory = ViewModelMovieDetailFactory(
                                    viewModelMovieDetailAssistedFactory,
                                    SavedStateHandle(
                                        mapOf(ID to id)
                                    )
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}