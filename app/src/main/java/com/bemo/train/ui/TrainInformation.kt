package com.bemo.train.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.bemo.train.Database.AppDatabase
import com.bemo.train.Database.ViewModel
import com.bemo.train.Entities.Stops
import com.bemo.train.R
import com.bemo.train.adapters.TrainStopsAdapter
import com.bemo.train.adapters.imageAdapter
import java.lang.Math.abs

class TrainInformation : AppCompatActivity() {



    private lateinit var stopAdapter: TrainStopsAdapter
    private lateinit var appDatabase: AppDatabase
    private lateinit var trainStopsRecyclerView: RecyclerView
    private lateinit var stopsList:  ArrayList<Stops>

    private lateinit var mViewModel: ViewModel



    private lateinit var adapter: imageAdapter
    private lateinit var handler: Handler
    private lateinit var viewPager2: ViewPager2
    private lateinit var imageList: ArrayList<Int>
    private lateinit var number:String
    private lateinit var type:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_train_information)

        trainStopsRecyclerView = findViewById(R.id.train_stations)
        number=intent.getStringExtra("number").toString()
        type=intent.getStringExtra("type").toString()


        init()

        getStopsData(number)

        setUpTransformer()

        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable,2000)
            }
        })

        stopAdapter.setOnItemClickListener(object : TrainStopsAdapter.onItemClickListener{
            override fun onItemClick(pos: Int) {

            }

        })
    }

    private fun getStopsData(number:String) {
        stopsList.clear()
        mViewModel.getStopsByTrainNumber(number).observe(this, Observer {
            stopsList.addAll(it)
            stopAdapter.notifyDataSetChanged()
        })

    }

    private val runnable = Runnable {
    viewPager2.currentItem= viewPager2.currentItem+1

}
    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer{ page ,position ->
val r= 1- abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }
        viewPager2.setPageTransformer(transformer)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)

    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable,2000)

    }
    private fun init(){
        appDatabase= AppDatabase.getInstance(this)

        stopsList = ArrayList()
        stopAdapter = TrainStopsAdapter(this,stopsList)
        trainStopsRecyclerView.layoutManager = LinearLayoutManager(this)
        trainStopsRecyclerView.adapter = stopAdapter

        mViewModel= ViewModelProvider(this).get(ViewModel::class.java)

        viewPager2 =findViewById(R.id.viewPager2)
        handler= Handler(Looper.myLooper()!!)
        imageList= ArrayList()

        imageList.add(R.drawable.vip_1)
        imageList.add(R.drawable.vip_3)
        imageList.add(R.drawable.vip_4)
        imageList.add(R.drawable.vip_2)
        adapter= imageAdapter(imageList,viewPager2)

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit=3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    fun track(view: View) {
        val intent =  Intent(this, TrackingActivity::class.java).also {
    it.putExtra("number",number)
         //   it.putExtra("type",type)
        }
        startActivity(intent)
    }
    private fun vipTrain(){

    }
    private fun sleepingTrain(){

    }
    private fun russianTrain(){

    }
    private fun thirdTrain(){

    }


}