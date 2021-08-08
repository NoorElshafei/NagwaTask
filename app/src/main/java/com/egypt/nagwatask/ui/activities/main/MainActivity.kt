package com.egypt.nagwatask.ui.activities.main

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.egypt.nagwatask.R
import com.egypt.nagwatask.di.DaggerAppComponent
import com.egypt.nagwatask.model.MovieModel
import com.egypt.nagwatask.ui.adapter.MovieAdapter
import com.egypt.nagwatask.util.JsonString
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var movieAdapter: MovieAdapter

    @Inject
    lateinit var gson: Gson

    private lateinit var flexboxLayoutManager: FlexboxLayoutManager

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val model: MainViewModel by viewModels()

        DaggerAppComponent.builder().build().inject(this)

        setUpRecyclerView()

        model.getMovies().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({ moviesList ->
                movieAdapter.setMovies(moviesList)
            }, { error ->
                Log.d(TAG, "onError: " + error.message)
                val movieList: MovieModel =
                    gson.fromJson(JsonString.jsonString, MovieModel::class.java)
                movieAdapter.setMovies(movieList)
                Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
            })
    }

    private fun setUpRecyclerView() {
        flexboxLayoutManager = FlexboxLayoutManager(this, FlexDirection.ROW, FlexWrap.WRAP)
        flexboxLayoutManager.justifyContent = JustifyContent.SPACE_BETWEEN
        movie_recyclerView.apply {
            hasFixedSize()
            layoutManager = flexboxLayoutManager
            adapter = movieAdapter
        }
    }
}