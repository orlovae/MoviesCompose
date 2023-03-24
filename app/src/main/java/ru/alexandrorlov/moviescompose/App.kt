package ru.alexandrorlov.moviescompose

import android.app.Application
import ru.alexandrorlov.moviescompose.di.AppComponent
import ru.alexandrorlov.moviescompose.di.DaggerAppComponent

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}