package ru.alexandrorlov.moviescompose.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component
    (modules = [AppModule::class, DatabaseModule::class, RemoteModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
        ): AppComponent
    }

    fun mainComponent(): MainComponent.Factory
}