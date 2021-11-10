package com.example.movierecycler.features.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movierecycler.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    lateinit var binding: FragmentFavoritesBinding

    private val viewModel by viewModels<FavMovieViewModel>()

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

        binding.favRecycler.adapter = adapter

        viewModel.liveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getFavMovies()
        }
    }
}