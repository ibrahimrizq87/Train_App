package com.bemo.train

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
private fun intent(){
    startActivity(Intent(this,Home::class.java))
    finish()
}

    override fun onStart() {
        super.onStart()
        val handler=Handler()
        handler.postDelayed({
            intent()
        },10)
    }
}