package com.gomez.herlin.my_application.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.gomez.herlin.my_application.ui.common.AspectRatioImageView
import com.gomez.herlin.my_application.R
import com.gomez.herlin.my_application.databinding.ActivityMainBinding
import com.gomez.herlin.my_application.model.Movie
import com.gomez.herlin.my_application.model.MovieDbClients
import com.gomez.herlin.my_application.model.TheMovieDbService
import com.gomez.herlin.my_application.ui.detail.DetailActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var movieDbService: TheMovieDbService

    private val moviesAdapter = MoviesAdapter(emptyList(), object : MovieClickListener {
        override fun onMovieClicked(movie: Movie) {
            navigateTo(movie)
        }
    })


    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        isGranted ->
        requestPopularMovies(isGranted)
        /*val message = when {
            isGranted -> "Permission granted"
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) -> "Should show rationale"
            else -> "Permission denied"
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()*/
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        binding.recicler.adapter = moviesAdapter

        binding.recicler.layoutManager = GridLayoutManager(this, 3)

        val cover = findViewById<AspectRatioImageView>(R.id.cover)
        cover.ratio = 1.5f

        requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)

        //val message = findViewById<TextView>(R.id.message)


    }

    @SuppressLint("MissingPermission")
    private fun requestPopularMovies(isLocationGrantend: Boolean) {

        if (isLocationGrantend) {
            fusedLocationClient.lastLocation.addOnCompleteListener {
                doRequestPopularMovies(getRegionFromLocation(it.result))
            }
        } else {
            doRequestPopularMovies("US")
        }

    }

    private fun doRequestPopularMovies(region: String) {
        lifecycleScope.launch {
            val apiKey = getString(R.string.api_key)
            val popularMovies = movieDbService.listPopularMovies(apiKey, region)

            moviesAdapter.movies = popularMovies.results
            moviesAdapter.notifyDataSetChanged()
        }
    }

    private fun getRegionFromLocation(location: Location?): String {
        if (location == null) {
            return "US"
        }
        val geocoder = Geocoder(this)
        val result = geocoder.getFromLocation(
            location.latitude,
            location.longitude,
            1
        )
        Log.d("MainActivity", "Country Code: ${result?.firstOrNull()?.countryCode ?: "US"}")
        return result?.firstOrNull()?.countryCode ?: "US"
    }

    private fun navigateTo(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}