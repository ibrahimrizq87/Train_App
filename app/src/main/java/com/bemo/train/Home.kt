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

    fun click(view: View) {
        startActivity(Intent(this,SearchByLocation::class.java))
    }
}