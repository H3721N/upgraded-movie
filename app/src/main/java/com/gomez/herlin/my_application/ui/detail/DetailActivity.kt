package com.gomez.herlin.my_application.ui.detail

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.bumptech.glide.Glide
import com.gomez.herlin.my_application.R
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
            appendInfo( R.string.original_languaje, movie.original_language)
            appendInfo( R.string.original_title, movie.original_title)
            appendInfo( R.string.relase_date, movie.release_date)
            appendInfo( R.string.popularity, movie.popularity.toString())
            appendInfo( R.string.vote_average, movie.vote_average.toString())
            /*bold { append("Original Lenguaje: ${movie.original_language}") }
            appendLine(movie.original_language)

            bold { append("Original Title: ${movie.original_title}") }
            appendLine(movie.original_title)

            bold { append("Release Date: ${movie.release_date}") }
            appendLine(movie.release_date)

            bold { append("Popularity: ${movie.popularity}") }
            appendLine(movie.popularity.toString())

            bold { append("Vote Average: ${movie.vote_average}") }
            appendLine(movie.vote_average.toString())*/
        }
    }

    private fun SpannableStringBuilder.appendInfo(stringRes: Int, value: String) {
        bold {
            append(getString(stringRes))
            append(": ")
        }
        appendLine(value)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}