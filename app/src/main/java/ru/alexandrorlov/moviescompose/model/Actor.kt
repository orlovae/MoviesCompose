package ru.alexandrorlov.moviescompose.model

import ru.alexandrorlov.moviescompose.R
import ru.alexandrorlov.moviescompose.config.ModelConfig.ERROR_INT
import ru.alexandrorlov.moviescompose.config.ModelConfig.ERROR_STRING

data class Actor(
    val id: Int = ERROR_INT,
    val name: String = ERROR_STRING,
    val photo: String = R.drawable.ic_launcher_error.toString()
)