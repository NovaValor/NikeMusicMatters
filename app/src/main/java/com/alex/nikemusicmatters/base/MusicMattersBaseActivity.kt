package com.alex.nikemusicmatters.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alex.nikemusicmatters.R

abstract class MusicMattersBaseActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}