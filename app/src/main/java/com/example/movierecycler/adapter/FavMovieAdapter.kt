package com.example.movierecycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movierecycler.R
import com.example.movierecycler.databinding.MovieItemBinding
import com.example.movierecycler.loadUrl
import com.example.movierecycler.model.FavoriteMovie

class FavMovieAdapter(val clickListener: (String) -> Unit) :
    ListAdapter<FavoriteMovie, FavMovieAdapter.FavMovieVH>(FavMovieDiffUtils()) {

    inner class FavMovieVH(val binding: MovieItemBinding) :
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


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):
            FavMovieAdapter.FavMovieVH {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return FavMovieVH(binding)

    }

    override fun onBindViewHolder(holder: FavMovieAdapter.FavMovieVH, position: Int) {
        holder.binding.root.animation = AnimationUtils.loadAnimation(
            holder.itemView.context,
            R.anim.recycler_animation
        )
        return holder.onBind(getItem(position))
    }
}

class FavMovieDiffUtils : DiffUtil.ItemCallback<FavoriteMovie>() {
    override fun areItemsTheSame(oldItem: FavoriteMovie, newItem: FavoriteMovie): Boolean {
        return oldItem.Poster == newItem.Poster
    }

    override fun areContentsTheSame(oldItem: FavoriteMovie, newItem: FavoriteMovie): Boolean {
        return oldItem.Title == newItem.Title
                && oldItem.Year == newItem.Year
                && oldItem.Poster == newItem.Poster
    }

}