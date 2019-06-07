package com.alex.nikemusicmatters.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alex.nikemusicmatters.R

abstract class MusicMattersBaseActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun navigateTo(fragment: Fragment, addToStack:Boolean = true){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.root_view, fragment, fragment.javaClass.simpleName)
        if (addToStack) transaction.addToBackStack(fragment.javaClass.simpleName)
        transaction.commit()
    }
}