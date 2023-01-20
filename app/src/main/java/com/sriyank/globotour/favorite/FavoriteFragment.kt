package com.sriyank.globotour.favorite

import android.icu.text.Transliterator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sriyank.globotour.R
import com.sriyank.globotour.city.City
import com.sriyank.globotour.city.CityAdapter
import com.sriyank.globotour.city.VacationSpots
import java.util.*
import kotlin.collections.ArrayList


class FavoriteFragment : Fragment() {

    private lateinit var favouritecitylist :ArrayList<City>
    private lateinit var favAdapter :FavouriteAdapter
  //  private lateinit var recyclerView:RecyclerView
   private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        setupRecycleView(view!!)
        return view
    }

    private fun setupRecycleView ( view: View?){
        val context = requireContext()
        favouritecitylist = VacationSpots.favoriteCityList as ArrayList<City>
        favAdapter = FavouriteAdapter(context , favouritecitylist)

        recyclerView = view?.findViewById<RecyclerView>(R.id.fav_recycler_view)!!
        recyclerView?.adapter = favAdapter

        val layoutmanager = LinearLayoutManager(context)
        layoutmanager.orientation = RecyclerView.VERTICAL
        recyclerView?.layoutManager = layoutmanager

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private val itemTouchHelper=ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN,ItemTouchHelper.RIGHT){
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, targetViewHolder: RecyclerView.ViewHolder): Boolean {
            val fromPosition = viewHolder.adapterPosition
            val toPosition = targetViewHolder.adapterPosition

            Collections.swap(favouritecitylist , fromPosition , toPosition)
            recyclerView.adapter?.notifyItemMoved(fromPosition , toPosition)

            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val deletedCity :City = favouritecitylist.get(position)

            deleteitem(position)
            updateCityList(deletedCity , false)

            Snackbar.make(recyclerView , "Deleted " , Snackbar.LENGTH_LONG).setAction("UNDO"){
                undodelete(position , deletedCity)
                updateCityList(deletedCity , true)
            }.show()

        }

    })

    private fun deleteitem(position :Int){
        favouritecitylist.removeAt(position)
        favAdapter.notifyItemRemoved(position)
        favAdapter.notifyItemRangeChanged(position , favouritecitylist.size)
       // FavouriteAdapter.notifyItemRemoved(position)
       // FavouriteAdapter.notifyItemRangeChanged(position , favouritecitylist.size)
    }

    private fun updateCityList(deletedcity :City , isfav:Boolean){
        val cityList = VacationSpots.cityList!!
        val position = cityList.indexOf(deletedcity)
        cityList[position].isFavorite = isfav
    }

    private fun undodelete(position:Int , deletedcity:City){
        favouritecitylist.add(position,deletedcity)
        favAdapter.notifyItemInserted(position)
        favAdapter.notifyItemRangeChanged(position,favouritecitylist.size)

    }

}
