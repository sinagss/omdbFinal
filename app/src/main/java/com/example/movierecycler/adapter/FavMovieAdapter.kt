package com.example.movierecycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movierecycler.databinding.MovieItemBinding
import com.example.movierecycler.loadUrl
import com.example.movierecycler.model.FavoriteMovie
import com.example.movierecycler.model.Movies


class FavMovieAdapter(val clickListener: (String) -> Unit) :
    ListAdapter<FavoriteMovie, FavMovieAdapter.FavMovieVH>(FavoriteMovieDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMovieVH =
        FavMovieVH(
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: FavMovieVH, position: Int) =
        holder.onBind(getItem(position))

    inner class FavMovieVH(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: FavoriteMovie) {
            binding.ivPoster.loadUrl(item.Poster)
            binding.tvMovieTitle.text = item.Title
            binding.tvYear.text = item.Year
            binding.root.setOnClickListener {
                clickListener(item.imdbID)
            }
        }
    }

    class FavoriteMovieDiffUtils : DiffUtil.ItemCallback<FavoriteMovie>() {
        override fun areItemsTheSame(oldItem: FavoriteMovie, newItem: FavoriteMovie) =
            oldItem.imdbID == newItem.imdbID

        override fun areContentsTheSame(oldItem: FavoriteMovie, newItem: FavoriteMovie) =
            oldItem.Title == newItem.Title &&
                    oldItem.Actors == newItem.Actors &&
                    oldItem.Plot == newItem.Plot

    }
}