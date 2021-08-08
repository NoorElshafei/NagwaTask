package com.egypt.nagwatask.data.networks

import com.egypt.nagwatask.model.MovieModel
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("movies")
    fun getMovies(): Single<MovieModel>
}