package com.egypt.nagwatask.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egypt.nagwatask.R
import com.egypt.nagwatask.model.MovieModel

class MovieAdapter(val movieList: ArrayList<MovieModel.MovieModelItem>) :
    RecyclerView.Adapter<MovieAdapter.CategoryViewHolder>() {

    fun setMovies(movieList: ArrayList<MovieModel.MovieModelItem>) {
        this.movieList.clear()
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_movie, parent, false)
        )

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        

    }


    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}