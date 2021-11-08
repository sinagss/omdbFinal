package com.example.movierecycler.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movierecycler.repository.local.MovieInfoDao
import com.example.movierecycler.model.FavoriteMovies

@Database(entities = [FavoriteMovies::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieInfoDao
}