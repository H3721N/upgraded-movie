package com.gomez.herlin.my_application.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface TheMovieDbService {

    @GET("movie/popular")
    suspend fun listPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): MOvieDbResult
}