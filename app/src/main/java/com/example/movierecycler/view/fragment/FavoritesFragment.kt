package com.example.movierecycler.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movierecycler.adapter.FavMovieAdapter
import com.example.movierecycler.databinding.FragmentFavoritesBinding
import com.example.movierecycler.model.AppDatabase
import com.example.movierecycler.model.FavoriteMovie
import com.example.movierecycler.model.Movies
import com.example.movierecycler.showLongToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    lateinit var binding: FragmentFavoritesBinding
    lateinit var favMovies: List<FavoriteMovie>

    @Inject
    lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FavMovieAdapter {
            findNavController().navigate(
                FavoritesFragmentDirections.actionFavoritesFragmentToMovieDetailFragment(it)
            )
        }

//        lifecycleScope.launch {
//            favMovies = db.movieDao().getAllMovies()
//            adapter.submitList(favMovies)
//        }

    }
}