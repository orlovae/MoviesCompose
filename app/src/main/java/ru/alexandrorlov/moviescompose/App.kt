package ru.alexandrorlov.moviescompose

import android.app.Application
import android.content.Context
import androidx.room.Room
import ru.alexandrorlov.moviescompose.config.DataLocalConfig
import ru.alexandrorlov.moviescompose.data.local.LocalDataBase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
    }
}