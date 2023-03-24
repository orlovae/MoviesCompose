package ru.alexandrorlov.moviescompose.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.alexandrorlov.moviescompose.screen.ViewModelMovieList

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(ViewModelMovieList::class)
    @Binds
    fun provideViewModelMovieList(viewModel: ViewModelMovieList): ViewModel
}