package ru.alexandrorlov.moviescompose.config

object DataLocalConfig {
    const val DATABASE_NAME = "movies.db"

    object Movie {
        const val TABLE_NAME = "Movies"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_POSTER = "poster"
        const val COLUMN_BACKDROP = "backdrop"
        const val COLUMN_DATE_RELEASE = "date_release"
        const val COLUMN_RATING = "rating"
        const val COLUMN_AGE_RATING = "age_rating"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_GENRE_ID = "genre_id"
        const val COLUMN_GENRE_NAME = "genre_name"
        const val COLUMN_ACTOR_ID = "actor_id"
        const val COLUMN_ACTOR_NAME = "actor_name"
        const val COLUMN_ACTOR_PHOTO = "actor_photo"
    }

    object Genre {
        const val TABLE_NAME = "Genres"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
    }
}