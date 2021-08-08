package com.egypt.nagwatask.di

import android.content.Context
import com.egypt.nagwatask.ui.activities.main.MainActivity
import com.egypt.nagwatask.ui.activities.main.MainViewModel
import com.egypt.nagwatask.ui.activities.main.MovieRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules=[AppModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(mainViewModel: MainViewModel)

    fun inject(movieRepository: MovieRepository)
}