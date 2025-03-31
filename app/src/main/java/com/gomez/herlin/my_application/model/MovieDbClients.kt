package com.gomez.herlin.my_application.model

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieDbClients {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create()).build()
        // .create(TheMovieDbService::class.java)

    val service = retrofit.create(TheMovieDbService::class.java)

}