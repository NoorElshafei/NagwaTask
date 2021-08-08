package com.egypt.nagwatask.ui.activities.main

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import com.egypt.nagwatask.model.MovieModel
import com.egypt.nagwatask.data.networks.ApiService
import com.egypt.nagwatask.data.networks.ApiServiceFactory
import com.egypt.nagwatask.di.DaggerAppComponent
import io.reactivex.Single
import javax.inject.Inject


class MainViewModel: ViewModel() {
    @Inject
    lateinit var movieRepository: MovieRepository

    init {
        Log.d(ContentValues.TAG, "noor init viemodel: ")
        DaggerAppComponent.create().inject(this)
        movieRepository.getMovies()
    }

}