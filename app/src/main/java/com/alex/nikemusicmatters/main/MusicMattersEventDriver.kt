package com.alex.nikemusicmatters.main

import com.alex.nikemusicmatters.models.Album
import com.alex.nikemusicmatters.models.MusicMattersViewModel

interface MusicMattersEventDriver {
    fun onSelectionEvent(album: Album)
    val featureViewModel:MusicMattersViewModel
}