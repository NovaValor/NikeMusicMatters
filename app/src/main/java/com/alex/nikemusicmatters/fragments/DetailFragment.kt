package com.alex.nikemusicmatters.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alex.nikemusicmatters.R
import com.alex.nikemusicmatters.main.MusicMattersEventDriver
import com.alex.nikemusicmatters.models.Album
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment:Fragment() {

    lateinit var eventDriver: MusicMattersEventDriver

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindAlbum(eventDriver.featureViewModel.selectedAlbum)
        nav_out_button.setOnClickListener {navOut()}
    }

    private fun bindAlbum(album: Album){
        Picasso.with(requireContext()).load(album.pngUrl).into(image)
        album_name.setText(album.name)
        artist.setText(album.artistName)
        genre.setText(album.genre)
        release.setText(album.releaseDate)
        copy.setText(album.copyInfo)
    }

    private fun navOut(){
        // just passing the uri with my intent, apple's deep links do the rest :)
        val openUrlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(eventDriver.featureViewModel.selectedAlbum.artistUrl))
        startActivity(openUrlIntent)
    }
}