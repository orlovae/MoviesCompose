package ru.alexandrorlov.moviescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import ru.alexandrorlov.moviescompose.model.Movie
import ru.alexandrorlov.moviescompose.screen.AppNavHost
import ru.alexandrorlov.moviescompose.screen.MovieComponent
import ru.alexandrorlov.moviescompose.screen.MoviesScreen
import ru.alexandrorlov.moviescompose.screen.MoviesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                AppNavHost(
                    modifier = Modifier
                )
            }
        }
    }
}

