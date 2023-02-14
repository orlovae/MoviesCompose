package ru.alexandrorlov.moviescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.alexandrorlov.moviescompose.screen.MoviesScreen
import ru.alexandrorlov.moviescompose.screen.MoviesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesScreen(
                moviesViewModel = MoviesViewModel()
            )
        }
    }
}

