package com.egypt.nagwatask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egypt.nagwatask.R
import com.egypt.nagwatask.data.model.MovieModel

class MovieAdapter(var movieList: ArrayList<MovieModel.MovieModelItem>, val context: Context) :
    RecyclerView.Adapter<MovieAdapter.CategoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie, parent, false
            )
        )

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {


    }


    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }


}