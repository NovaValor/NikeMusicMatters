package com.alex.nikemusicmatters.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alex.nikemusicmatters.R
import com.alex.nikemusicmatters.fragments.adapters.HomeFragmentRecyclerAdapter
import com.alex.nikemusicmatters.main.MusicMattersEventDriver
import com.alex.nikemusicmatters.models.Album
import com.alex.nikemusicmatters.models.MusicMattersViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment:Fragment() {

    lateinit var eventDriver: MusicMattersEventDriver
    lateinit var featureModel:MusicMattersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        featureModel = ViewModelProviders.of(requireActivity()).get(MusicMattersViewModel::class.java)
        featureModel.getAlbumsLiveData().observe(this, Observer { data -> bindContent(data) })
    }

    private fun bindContent(dataSet:ArrayList<Album>){
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.base_list_divider)!!)
        recycler.addItemDecoration(divider)

        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = HomeFragmentRecyclerAdapter(dataSet,{album -> eventDriver.onSelectionEvent(album)})
        recycler.layoutManager?.onRestoreInstanceState(featureModel.recyclerInstanceState)
    }

    override fun onPause() {
        super.onPause()
        featureModel.recyclerInstanceState = recycler.layoutManager?.onSaveInstanceState()
    }
}