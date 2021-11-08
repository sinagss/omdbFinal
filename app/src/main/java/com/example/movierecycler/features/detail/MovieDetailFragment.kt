package com.example.movierecycler.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.movierecycler.R
import com.example.movierecycler.base.AppDatabase
import com.example.movierecycler.databinding.FragmentMovieDetailBinding
import com.example.movierecycler.loadUrl
import com.example.movierecycler.model.FavoriteMovies
import com.example.movierecycler.model.MovieInfo
import com.example.movierecycler.repository.network.RetroInterface
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {
    lateinit var binding: FragmentMovieDetailBinding

    private val viewModel by viewModels<MovieDetailViewModel>()

    @Inject
    lateinit var db: AppDatabase

    @Inject
    lateinit var retroInterface: RetroInterface

    val args by navArgs<MovieDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.searchMovieById(args.omdbId)
        viewModel.liveData.observe(viewLifecycleOwner) {
            loadMovieInfoIntoView(it)

            lifecycleScope.launch(Dispatchers.Main) {
                if (viewModel.isSavedMovie(it.imdbID)) {
                    binding.btnSave.text = getString(R.string.btn_unsave)
                } else {
                    binding.btnSave.text = getString(R.string.btn_save)
                }
            }
        }

        binding.btnSave.setOnClickListener {
            viewModel.liveData.observe(viewLifecycleOwner) {
                lifecycleScope.launch(Dispatchers.Main) {
                    val favMovieInfo = FavoriteMovies(
                        it.imdbID, it.Title, it.Actors, it.Plot,
                        it.Year, it.Awards, it.Country, it.Director, it.Genre,
                        it.Language, it.Metascore, it.Poster, it.Rated, it.Released,
                        it.Runtime, it.Type, it.Writer, it.imdbRating, it.imdbVotes, it.totalSeasons
                    )

                    if (viewModel.isSavedMovie(it.imdbID)) {
                        viewModel.unsaveMovie(favMovieInfo)
                        binding.btnSave.text = getString(R.string.btn_save)
                    } else {
                        binding.btnSave.text = getString(R.string.btn_unsave)
                        viewModel.saveMovie(favMovieInfo)
                    }
                }
            }
        }
    }

    private fun loadMovieInfoIntoView(movieInfo: MovieInfo) {
        binding.txtMovieTitle.text = movieInfo.Title
        binding.tvActors.text = movieInfo.Actors
        binding.tvPlot.text = movieInfo.Plot
        binding.ivMoviePoster.loadUrl(movieInfo.Poster)
    }
}