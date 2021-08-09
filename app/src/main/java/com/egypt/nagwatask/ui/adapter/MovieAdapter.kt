package com.egypt.nagwatask.ui.adapter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.egypt.nagwatask.R
import com.egypt.nagwatask.model.MovieModel
import com.egypt.nagwatask.util.DialogCustom
import kotlinx.android.synthetic.main.item_movie.view.*


class MovieAdapter(private val movieList: ArrayList<MovieModel.MovieModelItem>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    lateinit var activity: FragmentActivity


    fun setMovies(movieList: ArrayList<MovieModel.MovieModelItem>) {
        this.movieList.clear()
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }

    fun setContextAdapter(activity: FragmentActivity) {
        this.activity = activity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie, parent, false
            )
        )

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (movieList[position].type == "VIDEO") {
            Glide.with(activity).load(R.drawable.play_thamb).into(holder.itemView.type_image)
            holder.itemView.watch_button.text = activity.getString(R.string.see_video)
        } else {
            Glide.with(activity).load(R.drawable.book_background1).into(holder.itemView.type_image)
            holder.itemView.watch_button.text = activity.getString(R.string.read_pdf)
        }
        holder.itemView.type_text.text = movieList[position].type
        holder.itemView.item_status.text = movieList[position].status
        holder.itemView.title_text.text = "name: ${movieList[position].name}"


        holder.itemView.watch_button.setOnClickListener {
            val uri: Uri = Uri.parse(movieList[position].url)
            val i = Intent(Intent.ACTION_VIEW, uri)
            activity.startActivity(i)
        }

        holder.itemView.download_button.setOnClickListener {
            holder.itemView.item_status.text = "Downloading"
            val bundle = Bundle()
            bundle.putParcelable("movieModel", movieList[position])
            val dialogCustom = DialogCustom()
            dialogCustom.arguments = bundle
            dialogCustom.show(activity?.supportFragmentManager, "Dialog Fragment")
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}