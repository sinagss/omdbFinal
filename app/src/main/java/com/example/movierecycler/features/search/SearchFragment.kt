package com.example.movierecycler.features.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movierecycler.databinding.FragmentSearchBinding
import com.example.movierecycler.hideKeyboard
import com.example.movierecycler.repository.network.RetroInterface
import com.example.movierecycler.showLongToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding

    private val viewModel by viewModels<SearchViewModel>()

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

        viewModel.liveData.observe(viewLifecycleOwner) {
            adapter.submitList(it.Search)
        }


        binding.btnSearch.setOnClickListener {
            val searchString = binding.etSearch.text.toString()
            showLongToast(requireContext(), "Searching for ${searchString}!")

            it.hideKeyboard()

            viewModel.searchMovie(searchString)

        }

        binding.btnClear.setOnClickListener { binding.etSearch.setText("") }
    }
}