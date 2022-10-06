package com.bemo.medicalservices.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bemo.train.R
import com.bemo.train.Station


class StationAdapter(val context:Context,val stationList:ArrayList<Station>)
    : RecyclerView.Adapter<StationAdapter.RegistrationViewHolder>() {
    private lateinit var onItemClick: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(pos:Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        onItemClick=listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistrationViewHolder {
        val view :View =LayoutInflater.from(context).inflate(R.layout.station_item,parent,false)
        return RegistrationViewHolder(view,onItemClick)
    }

    override fun onBindViewHolder(holder: RegistrationViewHolder, position: Int) {
        val currentStation = stationList[position]
        holder.stationName.text = currentStation.stationName

    }

    override fun getItemCount(): Int {
        return stationList.size
    }
    class RegistrationViewHolder(item: View,listener: onItemClickListener) :RecyclerView.ViewHolder(item){

        val stationName = item.findViewById<TextView>(R.id.station_name)

        init {
            itemView.setOnClickListener {
                listener.onItemClick( adapterPosition)
            }
        }

    }

}