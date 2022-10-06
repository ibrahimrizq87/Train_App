package com.bemo.train

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrainData : AppCompatActivity() {
private lateinit var appDatabase: AppDatabase
    private lateinit var text:TextView
    var number=""
    var arr=""
    var start=""
    var time=""
    var key1=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_train_data)
         appDatabase=AppDatabase.getInstance(this)

        val t_number=findViewById<EditText>(R.id.editText)
        val t_arr=findViewById<EditText>(R.id.editText2)
        val t_star=findViewById<EditText>(R.id.editText4)
        val t_time=findViewById<EditText>(R.id.editText3)
        val key=findViewById<EditText>(R.id.editText6)
        text=findViewById<TextView>(R.id.text)

        val bt_save=findViewById<Button>(R.id.button3)
        val bt_show=findViewById<Button>(R.id.button4)
        val bt_update=findViewById<Button>(R.id.butto)
        val bt_delete=findViewById<Button>(R.id.button14)

        bt_save.setOnClickListener {
        /*    number=t_number.text.toString()
            arr=t_arr.text.toString()
            start=t_star.text.toString()
            time=t_time.text.toString()
            key1=key.text.toString()
          */
            val train=Train(11164,"star","arr","1d5dkdkdd5")
            val station = Station("mivfsvdfnia")
            val stop = Stops(11164,"mivfsvdfnia","12:15 am")
            GlobalScope.launch(Dispatchers.IO) {
                appDatabase.trainDao().insertTrain(train)
                appDatabase.trainDao().insertStation(station)
                appDatabase.trainDao().insertStop(stop)


            }
            writeData()
        }
        bt_show.setOnClickListener {
            key1=key.text.toString()

            readData()
        }
        bt_update.setOnClickListener {
            number=t_number.text.toString()
            arr=t_arr.text.toString()
            start=t_star.text.toString()
            time=t_time.text.toString()
            key1=key.text.toString()
            updateData()
        }
        bt_delete.setOnClickListener {
            GlobalScope.launch {
                appDatabase.trainDao().deleteAll()
            }
        }

    }

    private fun updateData() {

        if (number.isNotEmpty()&&arr.isNotEmpty()&&start.isNotEmpty()&&time.isNotEmpty()){

            GlobalScope.launch(Dispatchers.IO) {
               // appDatabase.trainDao().update(start,time,number)
            }
            Toast.makeText(this,number+arr+start,Toast.LENGTH_SHORT).show()

        }else{
            Toast.makeText(this,"no data ",Toast.LENGTH_SHORT).show()

        }
    }

    private suspend fun show(train:Train){
        withContext(Dispatchers.Main){
            text.text=train.startStation
        }
    }
    private fun writeData(){
        if (number.isNotEmpty()&&arr.isNotEmpty()&&start.isNotEmpty()&&time.isNotEmpty()){
            val train=Train(
            number.toInt(),start,arr,time)
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
                show(train)
            }
           // Toast.makeText(this,train.arriveStation,Toast.LENGTH_SHORT).show()

        }
    }
}