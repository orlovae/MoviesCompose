package ru.alexandrorlov.moviescompose.model

data class Movie(
    val id: Int,
    val name: String,
    val photo: String,/*можно добавать по умолчанию ссылку на заглушку из drawable*/
    val dateRelease: String,
    val rating: String,
    val ageRating: String,
    val description: String
)
