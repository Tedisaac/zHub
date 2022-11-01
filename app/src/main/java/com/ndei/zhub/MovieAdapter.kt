package com.ndei.zhub

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ndei.zhub.databinding.MovieDetailsLayoutBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movieList = ArrayList<Result>()

    class ViewHolder(val binding: MovieDetailsLayoutBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieDetailsLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500"+movieList[position].poster_path)
            .into(holder.binding.movieImageView)
        holder.binding.movieTextView.text = movieList[position].title

    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setMoviesList(movieList: ArrayList<Result>){
        this.movieList = movieList
        notifyDataSetChanged()
    }
}