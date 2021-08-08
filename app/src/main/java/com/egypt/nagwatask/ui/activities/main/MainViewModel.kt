package com.egypt.nagwatask.ui.activities.main

import androidx.lifecycle.ViewModel
import com.egypt.nagwatask.model.MovieModel
import com.egypt.nagwatask.data.networks.ApiService
import com.egypt.nagwatask.data.networks.ApiServiceFactory
import io.reactivex.Single


class MainViewModel: ViewModel() {
    private val service: ApiService by lazy {
        ApiServiceFactory.getInstance()
    }
    fun getMovies(): Single<MovieModel> {
        return service.getMovies()
    }
}