package com.example.movierecycler.api

import com.example.movierecycler.model.MovieInfo
import com.example.movierecycler.model.MovieSearch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val APIKEY: String = "2898c254"

interface RetroInterface {
    @GET("/")
    fun searchMovieByTitle(
        @Query("s") name: String,
        @Query("apikey") apiKey: String = APIKEY
    ): Call<MovieSearch>

    @GET("/")
    fun searchMovieByID(
        @Query("i") id: String,
        @Query("apikey") apiKey: String = APIKEY
    ): Call<MovieInfo>


    companion object {
        var retroInterface: RetroInterface? = null

        fun getInstance(): RetroInterface {
            if (retroInterface == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://www.omdbapi.com")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
                retroInterface = retrofit.create(RetroInterface::class.java)
            }
            return retroInterface!!
        }
    }
}