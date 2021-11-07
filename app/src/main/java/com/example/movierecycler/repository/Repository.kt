package com.example.movierecycler.repository

import com.example.movierecycler.base.AppDatabase
import com.example.movierecycler.model.MovieInfo
import com.example.movierecycler.model.MovieSearch
import com.example.movierecycler.repository.network.RetroInterface
import javax.inject.Inject

class Repository @Inject constructor(val db: AppDatabase, val network: RetroInterface) {
    suspend fun searchMovieByTitle(title: String): MovieSearch {
        return network.searchMovieByTitle(title)
    }

    suspend fun searchMovieById(omdbId: String): MovieInfo {
        return network.searchMovieByID(omdbId)
    }
}