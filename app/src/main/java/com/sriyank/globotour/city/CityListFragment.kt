package com.sriyank.globotour.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sriyank.globotour.R


class CityListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_city_list, container, false)

        setupRecycleView(view)
        return view
    }

    private fun setupRecycleView ( view: View?){
        val context = requireContext()
        val cityAdapter = CityAdapter(context ,VacationSpots.cityList!!)

        val recyclerView = view?.findViewById<RecyclerView>(R.id.city_recycler_view)
        recyclerView?.adapter = cityAdapter

        val layoutmanager = LinearLayoutManager(context)
        layoutmanager.orientation = RecyclerView.VERTICAL
        recyclerView?.layoutManager = layoutmanager
    }

}