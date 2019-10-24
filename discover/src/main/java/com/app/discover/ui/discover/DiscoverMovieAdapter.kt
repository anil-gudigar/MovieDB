package com.app.discover.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.common.data.model.Movie
import com.app.discover.R
import com.app.discover.databinding.ListItemBinding


/**
 * Adapter for the [RecyclerView] in [DiscoverFragment].
 */
class DiscoverMovieAdapter : ListAdapter<Movie, DiscoverMovieAdapter.ViewHolder>(DiffCallback()) {
    val ARG_MOVIEID = "movieid"

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.apply {
            bind(createOnClickListener(movie.id), movie)
            itemView.tag = movie
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    private fun createOnClickListener(id: String): View.OnClickListener {
        return View.OnClickListener {
            val args = Bundle()
            args.putString(ARG_MOVIEID, id)
            val deeplink = Navigation.findNavController(it)
                .createDeepLink()
                .setDestination(R.id.navigation_details)
                .setArguments(args)
                .createPendingIntent()
            deeplink.send()
        }
    }

    class ViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.moiveItem) {

        fun bind(listener: View.OnClickListener, item: Movie) {
            binding.apply {
                clickListener = listener
                movie = item
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}