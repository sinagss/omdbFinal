package com.example.movierecycler.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movierecycler.adapter.MovieAdapter
import com.example.movierecycler.api.RetroInterface
import com.example.movierecycler.databinding.FragmentSearchBinding
import com.example.movierecycler.hideKeyboard
import com.example.movierecycler.model.MovieSearch
import com.example.movierecycler.showLongToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding

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

        val retrofitInterface = RetroInterface.getInstance()

        binding.btnSearch.setOnClickListener {
            val searchString = binding.etSearch.text.toString()
            showLongToast(requireContext(), "Searching for ${searchString}!")

            it.hideKeyboard()
            retrofitInterface.searchMovieByTitle(searchString)
                .enqueue(object : Callback<MovieSearch> {
                    override fun onResponse(
                        call: Call<MovieSearch>,
                        response: Response<MovieSearch>
                    ) {
                        adapter.submitList(response.body()?.Search)
                    }

                    override fun onFailure(call: Call<MovieSearch>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                    }
                })
        }

        binding.btnClear.setOnClickListener { binding.btnSearch.text = "" }
    }
}