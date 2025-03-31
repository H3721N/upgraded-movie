package com.gomez.herlin.my_application

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.gomez.herlin.my_application.databinding.ActivityMainBinding
import com.gomez.herlin.my_application.model.Movie
import com.gomez.herlin.my_application.model.MovieDbClients
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val moviesAdapter = MoviesAdapter(emptyList()) { movie ->
            navigateTo(movie)
        }

        /*val moviesAdapter = MoviesAdapter(emptyList(), object : MovieClickListener {
            override fun onMovieClicked(movie: Movie) {
                Toast.makeText(this@MainActivity, movie.title, Toast.LENGTH_SHORT).show()
            }
        })*/

        binding.recicler.adapter = moviesAdapter

        binding.recicler.layoutManager = GridLayoutManager(this, 2)

        lifecycleScope.launch {

            val apiKey = getString(R.string.api_key)
            val popularMovies = MovieDbClients.service.listPopularMovies(apiKey)

            moviesAdapter.movies = popularMovies.results
            moviesAdapter.notifyDataSetChanged()


        }

        val message = findViewById<TextView>(R.id.message)


    }

    private fun navigateTo(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_TITLE, movie.title)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}