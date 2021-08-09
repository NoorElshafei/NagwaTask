package com.egypt.nagwatask.ui.activities.main


import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egypt.nagwatask.data.networks.ApiService
import com.egypt.nagwatask.di.DaggerAppComponent
import com.egypt.nagwatask.model.MovieModel
import com.egypt.nagwatask.util.JsonString
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MovieRepository {
    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var gson: Gson

    private val _data by lazy { MutableLiveData<MovieModel>() }
    val data: LiveData<MovieModel>
        get() = _data

    private val _isError by lazy { MutableLiveData<String>() }
    val isError: LiveData<String>
        get() = _isError

    init {
        Log.d(TAG, "noor initiate: ")
        DaggerAppComponent.create().inject(this)
    }

    fun getFakeMovies() {

        Observable.create<MovieModel> { emitter ->
            val movieModel = gson.fromJson(JsonString.jsonString, MovieModel::class.java)
            emitter.onNext(movieModel)
        }.subscribe({ moviesList ->
            _data.postValue(moviesList)
            Log.d(TAG, "noor getMovies: " + moviesList)
        }, { error ->
            Log.d(TAG, "noor onError: " + error.message)
            _isError.postValue(error.message)
        })

    }

    fun getApiMovies() {
        apiService.getMovies().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ moviesList ->
                _data.postValue(moviesList)
                Log.d(TAG, "noor getMovies: " + moviesList)
            }, { error ->
                Log.d(TAG, "noor onError: " + error.message)
                _isError.postValue(error.message)
            })
    }
}