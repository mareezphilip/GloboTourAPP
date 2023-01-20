package com.sriyank.globotour.city

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.sriyank.globotour.R

import androidx.recyclerview.widget.RecyclerView

class CityAdapter(val context:Context , var cityList: ArrayList<City>) : RecyclerView.Adapter<CityAdapter.CityViewHolder>(){
    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener {
        private var currentPosition :Int = -1 ;
        private var currentCity :City? = null

        private val txtCityName = itemView.findViewById<TextView>(R.id.txv_city_name)
        private val imgCityImage = itemView.findViewById<ImageView>(R.id.imv_city)
        private val imvDelete = itemView.findViewById<ImageView>(R.id.imv_delete)
        private val imvFavorite = itemView.findViewById<ImageView>(R.id.imv_favorite)

        private val icFavoriteFilled = ResourcesCompat.getDrawable(context.resources , R.drawable.ic_favorite_filled , null )
        private val icFavoriteborder = ResourcesCompat.getDrawable(context.resources , R.drawable.ic_favorite_bordered , null )
        fun setData(city :City , position :Int){

            txtCityName.text = city.name
            imgCityImage.setImageResource(city.imageId)

            if(city.isFavorite){
                imvFavorite.setImageDrawable(icFavoriteFilled)
            }
            else{
                imvFavorite.setImageDrawable(icFavoriteborder)
            }

           this.currentPosition = position
           this.currentCity = city
        }

        fun setListeners(){
            imvDelete.setOnClickListener(this@CityViewHolder)
            imvFavorite.setOnClickListener(this@CityViewHolder)
        }

        override fun onClick(v: View?) {
           when (v!!.id){
               R.id.imv_delete -> deleteitem()
               R.id.imv_favorite -> addToFavorite()
           }

        }

        fun deleteitem(){
            cityList.removeAt(currentPosition)
            notifyItemRemoved(currentPosition)
            notifyItemRangeChanged(currentPosition , cityList.size)

            VacationSpots.favoriteCityList.remove(currentCity!!)
        }
        fun addToFavorite(){
            currentCity?.isFavorite = !(currentCity?.isFavorite!!)

            if(currentCity?.isFavorite!!){
               imvFavorite.setImageDrawable(icFavoriteFilled)
               VacationSpots.favoriteCityList.add(currentCity!!)
            }
            else{
                imvFavorite.setImageDrawable(icFavoriteborder)
                VacationSpots.favoriteCityList.remove(currentCity!!)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val itemView = LayoutInflater.from(context).inflate( R.layout.list_item_city, parent , false )
        return CityViewHolder(itemView)
    }

    override fun onBindViewHolder(cityviewholder: CityViewHolder, position: Int) {
        val city = cityList[position]
        cityviewholder.setData(city , position)
        cityviewholder.setListeners()
    }

    override fun getItemCount(): Int = cityList.size



}