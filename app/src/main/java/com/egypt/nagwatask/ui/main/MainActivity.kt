package com.egypt.nagwatask.ui.main


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.egypt.nagwatask.R
import com.egypt.nagwatask.di.DaggerAppComponent
import com.egypt.nagwatask.ui.adapter.MovieAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var movieAdapter: MovieAdapter

    @Inject
    lateinit var gson: Gson

    private lateinit var flexBoxLayoutManager: FlexboxLayoutManager

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerAppComponent.create().inject(this)

        setUpRecyclerView()

        observeIsError()
        observeMovieList()


    }

    private fun observeMovieList() {
        viewModel.movieRepository.data.observe(this, Observer {
            movieAdapter.setContextAdapter(this)
            movieAdapter.setMovies(it)
        })
    }

    private fun observeIsError() {
        viewModel.movieRepository.isError.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }


    private fun setUpRecyclerView() {
        flexBoxLayoutManager = FlexboxLayoutManager(this, FlexDirection.ROW, FlexWrap.WRAP)
        flexBoxLayoutManager.justifyContent = JustifyContent.SPACE_BETWEEN
        movie_recyclerView.apply {
            hasFixedSize()
            layoutManager = flexBoxLayoutManager
            adapter = movieAdapter
        }
    }


    fun updateMovies() {
        movieAdapter.notifyDataSetChanged()
    }
}