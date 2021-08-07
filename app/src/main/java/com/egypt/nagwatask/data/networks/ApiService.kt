package com.egypt.nagwatask.data.networks

import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("movies")
    fun getMovies(): Observable<String>
}