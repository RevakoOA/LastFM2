package com.ostapr.lastfm.com.ostapr.lastfm.main

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.ostapr.lastfm.R
import com.ostapr.model.Artist

class ArtistsListAdapter : PagingDataAdapter<Artist, ArtistPosterViewHolder>(MovieDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistPosterViewHolder =
        ArtistPosterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.artist_item, parent, false)
        )

    override fun onBindViewHolder(holder: ArtistPosterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object MovieDiffCallBack : DiffUtil.ItemCallback<Artist>() {
    override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem.mbid == newItem.mbid
    }

    override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem == newItem
    }
}

class ArtistPosterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val posterImageView = view.findViewById<ImageView>(R.id.imagePoster)
    private val nameTextView = view.findViewById<TextView>(R.id.textName)
    private val rankTextView = view.findViewById<TextView>(R.id.textRank)


    fun bind(artist: Artist?) {
        artist?.pickImageUrl()?.let { url ->
            posterImageView.load(url) {
                memoryCachePolicy(CachePolicy.ENABLED)
            }
        }
        nameTextView.text = artist?.name ?: ""
        rankTextView.text = "${artist?.rank}" ?: ""
    }
}