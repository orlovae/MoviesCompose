package ru.alexandrorlov.moviescompose.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.alexandrorlov.moviescompose.config.DataLocalConfig
import ru.alexandrorlov.moviescompose.data.local.LocalDataBase

@Module
class DatabaseModule {

    @Provides
    fun provideLocalDataBase(context: Context): LocalDataBase {
        return Room.databaseBuilder(
            context,
            LocalDataBase::class.java,
            DataLocalConfig.DATABASE_NAME
        )
            .build()
    }
}

