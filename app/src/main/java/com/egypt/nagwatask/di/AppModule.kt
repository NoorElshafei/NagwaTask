package com.egypt.nagwatask.di

import com.egypt.nagwatask.data.networks.ApiService
import com.egypt.nagwatask.data.networks.ApiServiceFactory
import com.egypt.nagwatask.model.MovieModel
import com.egypt.nagwatask.ui.main.MainViewModel
import com.egypt.nagwatask.ui.main.MovieRepository
import com.egypt.nagwatask.ui.adapter.MovieAdapter
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideApi(): ApiService = ApiServiceFactory.getInstance()

    @Provides
    fun provideViewModel(): MainViewModel = MainViewModel()

    @Provides
    fun provideMovieRepository(): MovieRepository = MovieRepository()

    @Provides
    fun provideGson() = Gson()

    @Provides
    fun provideMovieList() = MovieModel()

    @Provides
    fun provideMovieAdapter(movieList: MovieModel): MovieAdapter =
        MovieAdapter(movieList)
}