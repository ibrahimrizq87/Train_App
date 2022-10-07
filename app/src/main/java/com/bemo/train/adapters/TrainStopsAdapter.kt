package com.bemo.train.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bemo.medicalservices.adapters.StationAdapter
import com.bemo.train.R
import com.bemo.train.Entities.Station
import com.bemo.train.Entities.Stops
import com.bemo.train.Entities.Train


class TrainStopsAdapter(val context:Context,val stopsList:ArrayList<Stops>)
    : RecyclerView.Adapter<TrainStopsAdapter.RegistrationViewHolder>() {
    private lateinit var onItemClick: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(pos:Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        onItemClick=listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistrationViewHolder {

        val view :View =LayoutInflater.from(context).inflate(R.layout.train_stop_item,parent,false)
        return RegistrationViewHolder(view,onItemClick)
    }

    override fun onBindViewHolder(holder: RegistrationViewHolder, position: Int) {
        val currentStop = stopsList[position]
        holder.stationName.text = currentStop.stationName
        holder.fClassCost.text = currentStop.first_class_cost
        if ( currentStop.second_class_cost != "n" ) {
            holder.sClassCost.text = currentStop.second_class_cost
        }else{
            holder.sClassCost.text = ""
        }
        holder.time.text = "time: "+currentStop.time

    }

    override fun getItemCount(): Int {
        return stopsList.size
    }
    class RegistrationViewHolder(item: View,listener: onItemClickListener) :RecyclerView.ViewHolder(item){

        val stationName = item.findViewById<TextView>(R.id.st_name)
        val fClassCost = item.findViewById<TextView>(R.id.first_class_cost)
        val sClassCost = item.findViewById<TextView>(R.id.second_class_cost)
        val time = item.findViewById<TextView>(R.id.s_time)

        init {
            itemView.setOnClickListener {
                listener.onItemClick( adapterPosition)
            }
        }

    }

}