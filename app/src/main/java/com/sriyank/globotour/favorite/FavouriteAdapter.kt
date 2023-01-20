package com.sriyank.globotour.favorite

import android.content.Context
import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sriyank.globotour.R
import com.sriyank.globotour.city.City

class FavouriteAdapter(val context: Context, var favList: ArrayList<City>) : RecyclerView.Adapter<FavouriteAdapter.FavViewHolder> (){
    inner class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private var currentPosition :Int = -1 ;
        private var currentCity :City? = null
        private val  txtname_fav = itemView.findViewById<TextView>(R.id.txt_city_fav)
        private val  img_fav = itemView.findViewById<ImageView>(R.id.img_fav)

        fun setData (city: City , position: Int){
            txtname_fav.text = city.name
            img_fav.setImageResource(city.imageId)

            this.currentPosition = position
            this.currentCity = city
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val itemView = LayoutInflater.from(context).inflate( R.layout.list_item_favourite, parent , false )
        return FavViewHolder(itemView)
    }

    override fun onBindViewHolder(favviewholder: FavViewHolder, position: Int) {
        val city = favList[position]
        favviewholder.setData(city , position)
    }

    override fun getItemCount() : Int = favList.size


}