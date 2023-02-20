package ru.alexandrorlov.moviescompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.alexandrorlov.moviescompose.network.GenresNetwork
import ru.alexandrorlov.moviescompose.network.MovieDetailsNetwork
import ru.alexandrorlov.moviescompose.network.MoviesPopularNetwork
import ru.alexandrorlov.moviescompose.screen.AppNavHost

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

