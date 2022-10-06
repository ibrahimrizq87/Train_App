package com.bemo.train

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onBackPressed() {
        super.onBackPressed()
       finish()

    }


    fun enterData(view: View) {
        startActivity(Intent(this,TrainData::class.java))
    }
    fun searchByStation(view: View) {startActivity(Intent(this,SearchByLocation::class.java))}
    fun searchByNumber(view: View) {startActivity(Intent(this,SearchByNumber::class.java))}
}