package com.example.movierecycler.repository.network

import com.example.movierecycler.model.MovieInfo
import com.example.movierecycler.model.MovieSearch
import dagger.Provides
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val APIKEY: String = "2898c254"

interface RetroInterface {
    @GET("/")
    suspend fun searchMovieByTitle(
        @Query("s") name: String,
        @Query("apikey") apiKey: String = APIKEY
    ): MovieSearch

    @GET("/")
    suspend fun searchMovieByID(
        @Query("i") id: String,
        @Query("apikey") apiKey: String = APIKEY
    ): MovieInfo
}