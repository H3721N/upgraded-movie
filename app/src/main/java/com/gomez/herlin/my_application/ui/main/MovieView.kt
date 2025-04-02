package com.gomez.herlin.my_application.ui.main

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.gomez.herlin.my_application.R
import com.gomez.herlin.my_application.model.Movie

class MovieView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    val cover: ImageView
    val title: TextView

    init {
        val view = inflate(context, R.layout.view_movie, this)

        cover = view.findViewById(R.id.coverImg)
        title = view.findViewById(R.id.coverTitle)
    }

    fun setMovie(movie: Movie) {
        title.text = movie.title
        //cover.image = movie.cover
    }
}
