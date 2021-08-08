package com.egypt.nagwatask.di

import com.egypt.nagwatask.model.MovieModel
import com.egypt.nagwatask.ui.adapter.MovieAdapter
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideGson() = Gson()

    @Provides
    fun provideMovieList() = MovieModel()

    @Provides
    fun provideMovieAdapter(movieList: MovieModel): MovieAdapter =
        MovieAdapter(movieList)
}