package ru.alexandrorlov.moviescompose.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.alexandrorlov.moviescompose.App
import ru.alexandrorlov.moviescompose.config.DataLocalConfig
import ru.alexandrorlov.moviescompose.model.entity.GenreDb
import ru.alexandrorlov.moviescompose.model.entity.MovieDb

@Database(entities = [MovieDb::class], version = 1)
@TypeConverters(Converters::class)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun MovieDao(): MovieDao

    companion object {
        val instance: LocalDataBase by lazy {
            Room.databaseBuilder(
                App.appContext,
                LocalDataBase::class.java,
                DataLocalConfig.DATABASE_NAME)
                .build()
        }
    }
}
