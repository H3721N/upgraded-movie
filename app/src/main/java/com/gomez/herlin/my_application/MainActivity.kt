package com.gomez.herlin.my_application

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gomez.herlin.my_application.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        val message = findViewById<TextView>(R.id.message)

        binding.recicler.adapter = MoviesAdapter(
            listOf(
                Movie("Title 1", "https://loremflickr.com/320/240?random=1"),
                Movie("Title 2", "https://loremflickr.com/320/240?random=2"),
                Movie("Title 3", "https://loremflickr.com/320/240?random=3"),
                Movie("Title 4", "https://loremflickr.com/320/240?random=4"),
                Movie("Title 5", "https://loremflickr.com/320/240?random=5"),
            ),
            object : MovieClickListener {
                override fun onMovieClicked(movie: Movie) {
                    Toast.makeText(this@MainActivity, movie.title, Toast.LENGTH_SHORT).show()
                }
            }
        )

       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }
}