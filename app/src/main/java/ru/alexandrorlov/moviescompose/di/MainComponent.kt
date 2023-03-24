package ru.alexandrorlov.moviescompose.di

import dagger.Subcomponent
import ru.alexandrorlov.moviescompose.MainActivity
import ru.alexandrorlov.moviescompose.screen.ViewModelMovieDetail
import ru.alexandrorlov.moviescompose.screen.ViewModelMovieDetailFactory
import ru.alexandrorlov.moviescompose.screen.ViewModelMovieList

@AppScope
@Subcomponent(modules = [ViewModelModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(
        ): MainComponent
    }

    fun injectMainActivity(mainActivity: MainActivity)
}