package com.bemo.train.adapters



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bemo.train.Classes.Message
import com.bemo.train.R
import com.bemo.train.Entities.Station
import com.bemo.train.Entities.Stops


class MessageAdapter(val context:Context,val messageList:ArrayList<Message>)
    : RecyclerView.Adapter<MessageAdapter.RegistrationViewHolder>() {
    private lateinit var onItemClick: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(pos:Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        onItemClick=listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistrationViewHolder {
        val view :View =LayoutInflater.from(context).inflate(R.layout.message_item,parent,false)
        return RegistrationViewHolder(view,onItemClick)
    }

    override fun onBindViewHolder(holder: RegistrationViewHolder, position: Int) {
        val currentStation = messageList[position]
        //holder.stationName.text = currentStation.stationName

    }

    override fun getItemCount(): Int {
        return messageList.size
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