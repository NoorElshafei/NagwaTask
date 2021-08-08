package com.egypt.nagwatask.di

import android.content.Context
import com.egypt.nagwatask.ui.activities.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules=[AppModule::class])
interface AppComponent {



    fun inject(mainActivity: MainActivity)
}