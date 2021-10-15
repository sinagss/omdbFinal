package com.example.movierecycler.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movies(
    @PrimaryKey(autoGenerate = true) val movieId: Int,
    @ColumnInfo(name = "omdb_id") val omdbId: String,
    @ColumnInfo(name = "movie_title") val movieTitle: String?,
    @ColumnInfo(name = "actors") val actors: String?,
    @ColumnInfo(name = "plot") val plot: String?
)
