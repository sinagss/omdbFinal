package com.example.movierecycler.repository.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.movierecycler.model.Movies

@Dao
interface MovieInfoDao {
    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<Movies>

    @Query("SELECT * FROM movies WHERE movieId = :movieId")
    suspend fun findById(movieId: Int): Movies

    @Insert
    fun saveMovieInfo(movie: Movies)

    @Delete
    suspend fun unSaveMovieInfo(movie: Movies)
}