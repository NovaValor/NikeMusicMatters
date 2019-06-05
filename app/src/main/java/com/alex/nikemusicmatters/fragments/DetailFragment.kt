package com.alex.nikemusicmatters.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alex.nikemusicmatters.R
import com.alex.nikemusicmatters.main.MusicMattersEventDriver

class DetailFragment:Fragment() {

    lateinit var eventDriver: MusicMattersEventDriver

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }
}