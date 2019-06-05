package com.alex.nikemusicmatters.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.alex.nikemusicmatters.base.MusicMattersBaseActivity
import com.alex.nikemusicmatters.fragments.DetailFragment
import com.alex.nikemusicmatters.fragments.HomeFragment
import com.alex.nikemusicmatters.models.Album
import com.alex.nikemusicmatters.models.MusicMattersViewModel

class MainActivity : MusicMattersBaseActivity(), MusicMattersEventDriver {

    private lateinit var featureModel: MusicMattersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        featureModel = ViewModelProviders.of(this).get(MusicMattersViewModel::class.java)

        val homeFragment = HomeFragment()
        homeFragment.eventDriver = this
        navigateTo(homeFragment, false)
    }


    override fun onSelectionEvent(album: Album) {
        featureModel.selectedAlbum = album
        val detailFragment = DetailFragment()
        detailFragment.eventDriver = this
        navigateTo(detailFragment)
    }
}
