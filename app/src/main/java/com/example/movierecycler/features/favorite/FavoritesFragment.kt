package com.example.movierecycler.features.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movierecycler.databinding.FragmentFavoritesBinding
import com.example.movierecycler.base.AppDatabase
import com.example.movierecycler.model.FavoriteMovie
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    lateinit var binding: FragmentFavoritesBinding
    lateinit var favMovies: List<FavoriteMovie>

//    private val viewModel by

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