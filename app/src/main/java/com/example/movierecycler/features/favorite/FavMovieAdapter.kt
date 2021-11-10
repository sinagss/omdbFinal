package com.example.movierecycler.features.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movierecycler.databinding.MovieItemBinding
import com.example.movierecycler.loadUrl
import com.example.movierecycler.model.FavoriteMovies


class FavMovieAdapter(val clickListener: (String) -> Unit) :
    ListAdapter<FavoriteMovies, FavMovieAdapter.FavMovieVH>(FavoriteMovieDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMovieVH =
        FavMovieVH(
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: FavMovieVH, position: Int) =
        holder.onBind(getItem(position))

    inner class FavMovieVH(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: FavoriteMovies) {
            binding.ivPoster.loadUrl(item.poster)
            binding.tvMovieTitle.text = item.title
            binding.tvYear.text = item.year
            binding.tvMetaScore.text = item.metascore
            binding.root.setOnClickListener {
                clickListener(item.imdbId)
            }
        }
    }

    class FavoriteMovieDiffUtils : DiffUtil.ItemCallback<FavoriteMovies>() {
        override fun areItemsTheSame(oldItem: FavoriteMovies, newItem: FavoriteMovies) =
            oldItem.imdbId == newItem.imdbId

        override fun areContentsTheSame(oldItem: FavoriteMovies, newItem: FavoriteMovies) =
            oldItem.title == newItem.title &&
                    oldItem.actors == newItem.actors &&
                    oldItem.plot == newItem.plot

    }
}