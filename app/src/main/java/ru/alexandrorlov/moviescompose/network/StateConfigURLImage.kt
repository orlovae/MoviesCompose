package ru.alexandrorlov.moviescompose.network

sealed class StateConfigURLImage{
    object Loading: StateConfigURLImage()
    data class Error(val message: String): StateConfigURLImage()
    data class Success(val configURLImage: ConfigURLImage): StateConfigURLImage()
}