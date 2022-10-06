package com.bemo.train

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bemo.medicalservices.adapters.StationAdapter
import com.bemo.train.Entities.Station
import com.bemo.train.Entities.Train
import com.bemo.train.adapters.TrainAdapter

class SearchByNumber : AppCompatActivity() , SearchView.OnQueryTextListener{
    private lateinit var adapter: TrainAdapter
    private lateinit var appDatabase: AppDatabase
    private lateinit var trainRecyclerView: RecyclerView
    private lateinit var trainList: ArrayList<Train>
    lateinit var  trains: List<Train>

    private lateinit var mViewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_by_number)

        trains=ArrayList()
        setContentView(R.layout.activity_search_by_location)
        appDatabase=AppDatabase.getInstance(this)
        trainRecyclerView = findViewById(R.id.stations_recyclerview)

        trainList = ArrayList()
        adapter = TrainAdapter(this,trainList)
        trainRecyclerView.layoutManager = LinearLayoutManager(this)
        trainRecyclerView.adapter = adapter

        mViewModel= ViewModelProvider(this).get(ViewModel::class.java)
        getStationsData()


        adapter.setOnItemClickListener(object : TrainAdapter.onItemClickListener{
            override fun onItemClick(pos: Int) {
                Toast.makeText(this@SearchByNumber,trainList[pos].number, Toast.LENGTH_SHORT).show()
            }

        })

    }



override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.main_menu,menu)
    val search = menu?.findItem(R.id.search_menu)
    val searchView =search?.actionView as? SearchView
    searchView?.isSubmitButtonEnabled =true
    searchView?.setOnQueryTextListener(this)
    return true
}

private fun searchDatabase(query :String){
    val searchQuery= "%$query%"
    trainList.clear()
    mViewModel.getTrainByNumber(searchQuery).observe(this, Observer {list->
        list.let {
            trainList.addAll(it)
            adapter.notifyDataSetChanged()
        }

    })

    /* stationList.clear()
     val searchQuery= "%$query%"
     GlobalScope.launch {
         stations = appDatabase.trainDao().findStationByName(searchQuery)
     }
     stationList.addAll(stations)
     adapter.notifyDataSetChanged()
 */
}

override fun onQueryTextSubmit(query: String?): Boolean {
    if (query != null){
        searchDatabase(query)
    }else{
        getStationsData()
    }
    return true
}

override fun onQueryTextChange(newText: String?): Boolean {
    if (newText != null){
        searchDatabase(newText)
    }else{
        getStationsData()
    }
    return true
}
private  fun getStationsData(){
    trainList.clear()
    mViewModel.readAllTrainData.observe(this, Observer {
        trainList.addAll(it)
        adapter.notifyDataSetChanged()
    })


}/*
private fun getStationsData2(){
    stationList.clear()
    GlobalScope.launch {
        stations = appDatabase.trainDao().getAllStations2()
    }
    stationList.addAll(stations)
    adapter.notifyDataSetChanged()
}*/


}