package com.alex.nikemusicmatters.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.alex.nikemusicmatters.R
import com.alex.nikemusicmatters.models.Album
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_view_node.view.*

class HomeFragmentRecyclerAdapter(val dataSet: ArrayList<Album>, val onItemSelected:(Album) -> Unit) :
    RecyclerView.Adapter<HomeFragmentRecyclerAdapter.MusicItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicItemViewHolder {
        return MusicItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.album_view_node, parent, false))
    }

    override fun onBindViewHolder(holder: MusicItemViewHolder, position: Int) {
        holder.itemView.setOnClickListener { onItemSelected(dataSet[position]) }
        holder.album.setText(dataSet[position].name)
        holder.artist.setText(dataSet[position].artistName)
        bindImage(dataSet[position].pngUrl, holder.image)
    }

    override fun getItemCount(): Int = dataSet.size

    class MusicItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.image
        val album = itemView.album_name
        val artist = itemView.artist_name
    }

    fun bindImage(uri: String, receiver: ImageView) {
        Picasso.with(receiver.context).load(uri).into(receiver)
    }
}