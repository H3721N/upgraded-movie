package com.gomez.herlin.my_application.model

data class MOvieDbResult (
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
) {
/*    data class Movie(
        val id: Int,
        val title: String,
        val overview: String,
        val poster_path: String,
        val backdrop_path: String,
        val vote_average: Double,
        val release_date: String
    )*/
}