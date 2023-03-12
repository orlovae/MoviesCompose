package ru.alexandrorlov.moviescompose.data.remote

import ru.alexandrorlov.moviescompose.model.remote.ConfigURLImage

sealed class StateConfigURLImage{
    object Loading: StateConfigURLImage()
    data class Error(val message: String): StateConfigURLImage()
    data class Success(val configURLImage: ConfigURLImage): StateConfigURLImage()
}