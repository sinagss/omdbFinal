package com.example.movierecycler.features.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movierecycler.model.FavoriteMovies
import com.example.movierecycler.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavMovieViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    val liveData = MutableLiveData<List<FavoriteMovies>>()

    suspend fun getFavMovies() {
        liveData.postValue(repository.db.movieDao().getAllMovies())
    }

}