package com.example.movierecycler.features.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.movierecycler.base.AppDatabase
import com.example.movierecycler.databinding.FragmentMovieDetailBinding
import com.example.movierecycler.loadUrl
import com.example.movierecycler.model.MovieInfo
import com.example.movierecycler.model.Movies
import com.example.movierecycler.repository.network.RetroInterface
import com.example.movierecycler.showLongToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {
    lateinit var binding: FragmentMovieDetailBinding

    private lateinit var movieInfo: MovieInfo

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

//        var movie: Movies? = null

        val movieInfoResult = lifecycleScope.launch {
            retroInterface.searchMovieByID(args.omdbId)
        }




        viewModel.searchMovieById(args.omdbId)
        viewModel.liveData.observe(viewLifecycleOwner) {
            loadMovieInfoIntoView(it)
        }
//        lifecycleScope.launch(Dispatchers.IO) {
//            try {
//                movieInfo = retroInterface.searchMovieByID(args.omdbId)
//                movie = Movies(
//                    0,
//                    args.omdbId,
//                    movieInfo.Title,
//                    movieInfo.Actors,
//                    movieInfo.Plot
//                )
//            } catch (e: Exception) {
//                Log.d("TAG", e.toString())
//            }
//            launch(Dispatchers.Main) {
//                loadMovieInfoIntoView(movieInfo)
//            }
//        }


//        binding.btnSave.setOnClickListener {
//            lifecycleScope.launch(Dispatchers.IO) {
//                db.movieDao().saveMovieInfo(movie!!)
//                launch(Dispatchers.Main) {
//                    showLongToast(context, "Movie info of ${movie!!.movieTitle} added to db")
//
//                }
//            }
//        }
    }

    private fun loadMovieInfoIntoView(movieInfo: MovieInfo) {
        binding.txtMovieTitle.text = movieInfo.Title
        binding.tvActors.text = movieInfo.Actors
        binding.tvPlot.text = movieInfo.Plot
        binding.ivMoviePoster.loadUrl(movieInfo.Poster)
    }

}