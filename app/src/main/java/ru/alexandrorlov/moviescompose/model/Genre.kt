package ru.alexandrorlov.moviescompose.model

import ru.alexandrorlov.moviescompose.config.ModelConfig.ERROR_INT
import ru.alexandrorlov.moviescompose.config.ModelConfig.ERROR_STRING

data class Genre(
    val id: Int = ERROR_INT,
    val name: String = ERROR_STRING
)