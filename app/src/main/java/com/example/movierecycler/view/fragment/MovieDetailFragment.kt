package com.example.movierecycler.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movierecycler.api.RetroInterface
import com.example.movierecycler.databinding.FragmentMovieDetailBinding
import com.example.movierecycler.loadUrl
import com.example.movierecycler.model.AppDatabase
import com.example.movierecycler.model.MovieInfo
import com.example.movierecycler.model.Movies
import com.example.movierecycler.showLongToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {
    lateinit var binding: FragmentMovieDetailBinding

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
//        return super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var movie: Movies? = null
//        val db: AppDatabase by inject()

//        val retroInterface = RetroInterface.getInstance()

        Log.d("OMDB_ID", args.omdbId)
        retroInterface.searchMovieByID(args.omdbId).enqueue(
            object : Callback<MovieInfo> {
                override fun onResponse(call: Call<MovieInfo>, response: Response<MovieInfo>) {
                    val responseBody = response.body()
                    Log.d("RESPONSE", responseBody.toString())
                    binding.txtMovieTitle.text = responseBody?.Title
                    binding.tvActors.text = responseBody?.Actors
                    binding.tvPlot.text = responseBody?.Plot
                    binding.ivMoviePoster.loadUrl(responseBody?.Poster!!)

                    movie = Movies(
                        0,
                        args.omdbId,
                        responseBody.Title,
                        responseBody.Actors,
                        responseBody.Plot
                    )
                }

                override fun onFailure(call: Call<MovieInfo>, t: Throwable) {
                    showLongToast(context, t.toString())
                    Log.d("RESPONSE_ERROR", t.toString())
                    Log.d("RESPONSE_ERROR", t.message.toString())
                    Log.d("RESPONSE_ERROR", t.stackTraceToString())
                }
            }
        )

//        val saved = getSavedStatus(db, movie)

        binding.btnSave.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) { db.movieDao().saveMovieInfo(movie!!) }
            showLongToast(context, "Movie info of ${movie!!.movieTitle} added to db")
        }
    }

//    private fun getSavedStatus(db: AppDatabase, movie: Movies?): Boolean =
//        db.movieDao().findById(movie!!.movieId).saved
//
}