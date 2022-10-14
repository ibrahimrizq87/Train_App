package com.bemo.train.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bemo.train.Database.AppDatabase
import com.bemo.train.Database.ViewModel
import com.bemo.train.Entities.Station
import com.bemo.train.Entities.Stops
import com.bemo.train.Entities.Train
import com.bemo.train.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TrainData : AppCompatActivity() {
    private lateinit var appDatabase: AppDatabase
    private lateinit var text: TextView
    var number = ""
    var arr = ""
    var start = ""
    var time = ""
    var key1 = ""
    private lateinit var mViewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_train_data)
        appDatabase = AppDatabase.getInstance(this)
        mViewModel= ViewModelProvider(this).get(ViewModel::class.java)

        val t_number = findViewById<EditText>(R.id.editText)
        val t_arr = findViewById<EditText>(R.id.editText2)
        val t_star = findViewById<EditText>(R.id.editText4)
        val t_time = findViewById<EditText>(R.id.editText3)
        val key = findViewById<EditText>(R.id.editText6)
        text = findViewById<TextView>(R.id.text)

        val bt_save = findViewById<Button>(R.id.button3)
        val bt_show = findViewById<Button>(R.id.button4)
        val bt_update = findViewById<Button>(R.id.butto)
        val bt_delete = findViewById<Button>(R.id.button14)

        bt_save.setOnClickListener {
            val station88 = Station("assuit")
            val station2 = Station("minia")
            val station3 = Station("sohag")
            val station4 = Station("qena")
            val station5 = Station("malwy")
            val station6 = Station("cairo")
            val station7 = Station("alex")
            val station8 = Station("damnhor")
            val station9 = Station("bany swaf")

            val train = Train("163","assuit","cairo","12:15 am","10:00 pm","VIP")
            val train2 = Train("164","cairo","alex","1:15 am","12:00 pm","VIP")
            val train3 = Train("185","sohag","cairo","2:15 am","1:00 am","VIP")
            val train4 = Train("2010","minia","cairo","5:15 am","2:00 am","VIP")
            val train5 = Train("998","aswan","cairo","6:15 am","10:00 am","VIP")
            val train6 = Train("505","assui","alex","9:15 am","10:00 am","VIP")
            val train7 = Train("6060","assuit","cairo","11:15 am","10:00 am","VIP")
            val train8 = Train("70","alex","cairo","12:15 pm","10:00 pm","VIP")
            val train9 = Train("8090","cairo","assuit","2:15 pm","10:00 pm","VIP")

            val stop11 = Stops("163","cairo","12:15 am","20","45")
            val stop22 = Stops("163","assuit","12:15 am","20","45")
            val stop33 = Stops("163","alex","12:15 am","20","45")
            val stop44 = Stops("163","minia","12:15 am","20","45")
            val stop55 = Stops("163","bany swaf","12:15 am","20","45")
            val stop66 = Stops("163","damnhor","12:15 am","20","45")

            val stop2 = Stops("164","cairo","12:15 am","20","45")
            val stop3 = Stops("185","cairo","12:15 am","20","45")
            val stop4 = Stops("2010","alex","12:15 am","20","45")
            val stop5 = Stops("998","alex","12:15 am","20","45")
            val stop6 = Stops("505","assuit","12:15 am","20","45")
            val stop7 = Stops("6060","minia","12:15 am","20","45")
            val stop8 = Stops("70","assuit","12:15 am","20","45")
            val stop9 = Stops("8090","minia","12:15 am","20","45")




            mViewModel.addStations(station88)
            mViewModel.addStations(station2)
            mViewModel.addStations(station3)
            mViewModel.addStations(station4)
            mViewModel.addStations(station5)
            mViewModel.addStations(station6)
            mViewModel.addStations(station7)
            mViewModel.addStations(station8)
            mViewModel.addStations(station9)

            mViewModel.addTrains(train)
            mViewModel.addTrains(train2)
            mViewModel.addTrains(train3)
            mViewModel.addTrains(train4)
            mViewModel.addTrains(train5)
            mViewModel.addTrains(train6)
            mViewModel.addTrains(train7)
            mViewModel.addTrains(train8)
            mViewModel.addTrains(train9)




            mViewModel.addStops(stop11)
            mViewModel.addStops(stop2)
            mViewModel.addStops(stop3)
            mViewModel.addStops(stop4)
            mViewModel.addStops(stop5)
            mViewModel.addStops(stop6)
            mViewModel.addStops(stop22)
            mViewModel.addStops(stop33)
            mViewModel.addStops(stop44)
            mViewModel.addStops(stop55)
            mViewModel.addStops(stop66)

            mViewModel.addStops(stop7)
            mViewModel.addStops(stop8)
            mViewModel.addStops(stop9)



        }

        bt_show.setOnClickListener {
            key1 = key.text.toString()

           // readData()
        }
        bt_update.setOnClickListener {
            number = t_number.text.toString()
            arr = t_arr.text.toString()
            start = t_star.text.toString()
            time = t_time.text.toString()
            key1 = key.text.toString()
         //   updateData()
        }
        bt_delete.setOnClickListener {
            GlobalScope.launch {
                appDatabase.trainDao().deleteAll()
            }
        }

    }

    /* private fun updateData() {

        if (number.isNotEmpty()&&arr.isNotEmpty()&&start.isNotEmpty()&&time.isNotEmpty()){

            GlobalScope.launch(Dispatchers.IO) {
               // appDatabase.trainDao().update(start,time,number)
            }
            Toast.makeText(this,number+arr+start,Toast.LENGTH_SHORT).show()

        }else{
            Toast.makeText(this,"no data ",Toast.LENGTH_SHORT).show()

        }
    }

    private suspend fun show(train: Train){
        withContext(Dispatchers.Main){
            text.text=train.startStation
        }
    }
    private fun writeData(){
        if (number.isNotEmpty()&&arr.isNotEmpty()&&start.isNotEmpty()&&time.isNotEmpty()){
            val train= Train(
            number,start,arr,time)
            GlobalScope.launch(Dispatchers.IO) {
                appDatabase.trainDao().insertTrain(train)


            }
            Toast.makeText(this,number+start+arr+time,Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"enter data",Toast.LENGTH_SHORT).show()
        }

    }
    private fun readData(){
        if (key1.isNotEmpty()){

            GlobalScope.launch {
                var train=appDatabase.trainDao().findByNumber(key1)
              //  show(train)
            }
           // Toast.makeText(this,train.arriveStation,Toast.LENGTH_SHORT).show()

        }
    }
*/
}