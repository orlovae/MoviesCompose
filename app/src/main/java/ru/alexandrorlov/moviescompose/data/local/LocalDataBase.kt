package ru.alexandrorlov.moviescompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.alexandrorlov.moviescompose.model.entity.GenreDb
import ru.alexandrorlov.moviescompose.model.entity.MovieDb

@Database(entities = [MovieDb::class, GenreDb::class], version = 1)
@TypeConverters(Converters::class)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun MovieDao(): MovieDao

    abstract fun GenreDao(): GenreDao
}
