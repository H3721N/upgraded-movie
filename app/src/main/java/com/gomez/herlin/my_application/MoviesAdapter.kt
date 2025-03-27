package com.gomez.herlin.my_application

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gomez.herlin.my_application.databinding.ViewMovieItemBinding

interface MovieClickListener {
    fun onMovieClicked(movie: Movie)
}

class MoviesAdapter(private val movies: List<Movie>, private val movieClickListener: MovieClickListener) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //val binding = LayoutInflater.from(parent.context).inflate(R.layout.view_movie_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.itemView.setOnClickListener{
            movieClickListener.onMovieClicked(movies[position])
        }
    }

    class ViewHolder(private val binding: ViewMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.title.text = movie.title
            Glide.with(binding.root.context)
                .load(movie.cover)
                .into(binding.cover)
        }
    }
}