package com.gomez.herlin.my_application

import android.os.Bundle
import android.widget.TextView
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.gomez.herlin.my_application.databinding.ActivityDetailBinding
import com.gomez.herlin.my_application.model.Movie

class DetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_MOVIE = "DetailActivity:movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        if(movie != null) {
            title = movie.title
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w780${movie.backdrop_path}")
                .into(binding.backdrop)
            binding.summary.text = movie.overview
            bindDetailInfo(binding.detailInfo, movie)
        }


    }

    private fun bindDetailInfo(detailInfo: TextView, movie: Movie) {
        detailInfo.text = buildSpannedString {
            bold { append("Original Lenguaje: ${movie.original_language}") }
            appendLine(movie.original_language)

            bold { append("Original Title: ${movie.original_title}") }
            appendLine(movie.original_title)

            bold { append("Release Date: ${movie.release_date}") }
            appendLine(movie.release_date)

            bold { append("Popularity: ${movie.popularity}") }
            appendLine(movie.popularity.toString())

            bold { append("Vote Average: ${movie.vote_average}") }
            appendLine(movie.vote_average.toString())
        }
    }
}