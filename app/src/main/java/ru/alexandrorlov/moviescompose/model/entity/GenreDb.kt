package ru.alexandrorlov.moviescompose.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.alexandrorlov.moviescompose.config.DataLocalConfig

@Entity (tableName = DataLocalConfig.Genre.TABLE_NAME)
data class GenreDb(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = DataLocalConfig.Genre.COLUMN_ID)
    val id: Int,
    @ColumnInfo(name = DataLocalConfig.Genre.COLUMN_NAME)
    val name: String,
)