package ru.alexandrorlov.moviescompose.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import ru.alexandrorlov.moviescompose.R
import ru.alexandrorlov.moviescompose.config.DataLocalConfig
import ru.alexandrorlov.moviescompose.config.ModelConfig

@Entity(tableName = DataLocalConfig.Movie.TABLE_NAME)
data class MovieDb(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = DataLocalConfig.Movie.COLUMN_ID)
    val id: Int,
    @ColumnInfo(name = DataLocalConfig.Movie.COLUMN_TITLE)
    val title: String,
    @ColumnInfo(name = DataLocalConfig.Movie.COLUMN_POSTER)
    val poster: String,
    @ColumnInfo(name = DataLocalConfig.Movie.COLUMN_BACKDROP)
    val backdrop: String = R.drawable.ic_launcher_error.toString(),
    @ColumnInfo(name = DataLocalConfig.Movie.COLUMN_DATE_RELEASE)
    val dateRelease: String,
    @ColumnInfo(name = DataLocalConfig.Movie.COLUMN_RATING)
    val rating: Int,
    @ColumnInfo(name = DataLocalConfig.Movie.COLUMN_AGE_RATING)
    val ageRating: String,
    @ColumnInfo(name = DataLocalConfig.Movie.COLUMN_DESCRIPTION)
    val description: String,
    @ColumnInfo(name = DataLocalConfig.Movie.COLUMN_GENRE_ID)
    val genreId: List<Int> = emptyList(),
    @ColumnInfo(name = DataLocalConfig.Movie.COLUMN_GENRE_NAME)
    val genreName: List<String> = emptyList(),
    @ColumnInfo(name = DataLocalConfig.Movie.COLUMN_ACTOR_ID)
    val actorId: List<Int> = emptyList(),
    @ColumnInfo(name = DataLocalConfig.Movie.COLUMN_ACTOR_NAME)
    val actorName: List<String> = emptyList(),
    @ColumnInfo(name = DataLocalConfig.Movie.COLUMN_ACTOR_PHOTO)
    val actorPhoto: List<String> = emptyList()
)