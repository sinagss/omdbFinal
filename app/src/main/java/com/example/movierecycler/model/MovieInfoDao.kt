package com.example.movierecycler.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieInfoDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<Movies>

    @Query("SELECT * FROM movies WHERE movieId = :movieId")
    fun findById(movieId: Int): Movies

    @Insert
    fun saveMovieInfo(movie: Movies)

    @Delete
    fun unSaveMovieInfo(movie: Movies)
}