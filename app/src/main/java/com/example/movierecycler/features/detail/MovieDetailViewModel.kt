package com.example.movierecycler.features.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movierecycler.model.MovieInfo
import com.example.movierecycler.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    val liveData = MutableLiveData<MovieInfo>()

    fun searchMovieById(omdbId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.searchMovieById(omdbId)
            liveData.postValue(result)
        }
    }
}