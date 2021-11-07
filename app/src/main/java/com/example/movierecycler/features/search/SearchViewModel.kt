package com.example.movierecycler.features.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movierecycler.model.MovieSearch
import com.example.movierecycler.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    val liveData = MutableLiveData<MovieSearch>()

    fun searchMovie(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.searchMovieByTitle(title)
            liveData.postValue(result)
        }
    }
}