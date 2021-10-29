package com.example.movierecycler.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movierecycler.adapter.MovieAdapter
import com.example.movierecycler.api.RetroInterface
import com.example.movierecycler.databinding.FragmentSearchBinding
import com.example.movierecycler.hideKeyboard
import com.example.movierecycler.model.MovieSearch
import com.example.movierecycler.showLongToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var retrofitInterface: RetroInterface

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MovieAdapter {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToMovieDetailFragment(it)
            )
        }

        binding.moviesRecycler.adapter = adapter

        binding.btnSearch.setOnClickListener {
            val searchString = binding.etSearch.text.toString()
            showLongToast(requireContext(), "Searching for ${searchString}!")

            it.hideKeyboard()

            lifecycleScope.launch(Dispatchers.Main) {
                try {
                    val result = retrofitInterface.searchMovieByTitle(searchString)
                    adapter.submitList(result.Search)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        binding.btnClear.setOnClickListener { binding.etSearch.setText("") }
    }
}