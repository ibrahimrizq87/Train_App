package com.bemo.train.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bemo.train.Classes.Message
import com.bemo.train.Database.AppDatabase
import com.bemo.train.Database.ViewModel
import com.bemo.train.Entities.Stops
import com.bemo.train.R
import com.bemo.train.adapters.PassengerTrackingAdapter
import com.bemo.train.adapters.TrainStopsAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TrackingActivity : AppCompatActivity() {
    lateinit var calendar: Calendar
    lateinit var sampleDateFormat: SimpleDateFormat

    private lateinit var database: DatabaseReference

    lateinit var message:EditText
    lateinit var send :Button
    lateinit var trainNumber:String


    private lateinit var passengerAdapter: PassengerTrackingAdapter
    private lateinit var appDatabase: AppDatabase
    private lateinit var passengerRecyclerView: RecyclerView
    private lateinit var stopsList:  ArrayList<Stops>

    private lateinit var mViewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracking)
        appDatabase= AppDatabase.getInstance(this)
        database = Firebase.database.reference
        calendar= Calendar.getInstance()
        sampleDateFormat=SimpleDateFormat("EEE h:mm a")
        stopsList = ArrayList()
        passengerAdapter = PassengerTrackingAdapter(this,stopsList)
        passengerRecyclerView.layoutManager = LinearLayoutManager(this)
        passengerRecyclerView.adapter = passengerAdapter

        mViewModel= ViewModelProvider(this).get(ViewModel::class.java)


        passengerRecyclerView = findViewById(R.id.train_tracking_recycler)


        message=findViewById(R.id.message_text)
        send=findViewById(R.id.send_bt)
        trainNumber=intent.getStringExtra("number").toString()
        getStopsData(trainNumber)

  passengerAdapter.setOnItemClickListener(object : PassengerTrackingAdapter.onItemClickListener{
      override fun onItemClick(pos: Int) {

          addStationData( stopsList[pos].stationName,sampleDateFormat.format(calendar.time))
      }

  })
    }
    private fun getStopsData(number:String) {
        stopsList.clear()
        mViewModel.getStopsByTrainNumber(number).observe(this, Observer {
            stopsList.addAll(it)
            passengerAdapter.notifyDataSetChanged()
        })

    }




    private fun addStationData( stationName:String,time:String){
        database.child(trainNumber).child("passenger data").push().setValue(Message("train arrived here",time,trainNumber,stationName  ))
            .addOnSuccessListener {

                Toast.makeText(this,"data pushed", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this,it.toString(), Toast.LENGTH_LONG).show()
            }
    }
}