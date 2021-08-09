package com.egypt.nagwatask.di

import com.egypt.nagwatask.ui.main.MainActivity
import com.egypt.nagwatask.ui.main.MainViewModel
import com.egypt.nagwatask.ui.main.MovieRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules=[AppModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(mainViewModel: MainViewModel)

    fun inject(movieRepository: MovieRepository)
}