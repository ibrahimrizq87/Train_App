package com.bemo.train.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bemo.train.R
import com.bemo.train.Entities.Station
import com.bemo.train.Entities.Train


class TrainAdapter(val context:Context,val trainList:ArrayList<Train>)
    : RecyclerView.Adapter<TrainAdapter.RegistrationViewHolder>() {
    private lateinit var onItemClick: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(pos:Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        onItemClick=listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistrationViewHolder {
        val view :View =LayoutInflater.from(context).inflate(R.layout.train_list_item,parent,false)
        return RegistrationViewHolder(view,onItemClick)
    }

    override fun onBindViewHolder(holder: RegistrationViewHolder, position: Int) {
        val currentTrain = trainList[position]
       holder.trainNumber.text = currentTrain.number
        holder.startingStation.text = "Starting from :"+currentTrain.startStation+"  "+currentTrain.startTime
        holder.arrivingStation.text = "destination :"+currentTrain.arriveStation+"  "+currentTrain.arriveTime

    }

    override fun getItemCount(): Int {
        return trainList.size
    }
    class RegistrationViewHolder(item: View,listener: onItemClickListener) :RecyclerView.ViewHolder(item){

        val trainNumber = item.findViewById<TextView>(R.id.train_number_text)
        val startingStation = item.findViewById<TextView>(R.id.starting_station)
        val arrivingStation = item.findViewById<TextView>(R.id.arriving_station)


        init {
            itemView.setOnClickListener {
                listener.onItemClick( adapterPosition)
            }
        }

    }

}