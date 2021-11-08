package com.example.movierecycler.repository.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.movierecycler.model.FavoriteMovies
import com.example.movierecycler.model.MovieInfo

@Dao
interface MovieInfoDao {
    @Query("SELECT * FROM favMovies")
    suspend fun getAllMovies(): List<FavoriteMovies>

    @Query("SELECT * FROM favMovies WHERE imdbId = :imdbId")
    suspend fun findById(imdbId: String): FavoriteMovies

    @Query("SELECT EXISTS(SELECT * FROM favMovies WHERE imdbId = :imdbId)")
    suspend fun isMovieSaved(imdbId: String): Boolean

    @Insert(onConflict = REPLACE)
    fun saveMovieInfo(movie: FavoriteMovies)

    @Delete
    suspend fun unSaveMovieInfo(movie: FavoriteMovies)
}