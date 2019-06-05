package com.alex.nikemusicmatters.main

import com.alex.nikemusicmatters.models.Album

interface MusicMattersEventDriver {
    fun onSelectionEvent(album: Album)
}