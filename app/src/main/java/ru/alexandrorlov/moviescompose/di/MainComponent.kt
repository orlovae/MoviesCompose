package ru.alexandrorlov.moviescompose.di

import dagger.Subcomponent
import ru.alexandrorlov.moviescompose.MainActivity

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