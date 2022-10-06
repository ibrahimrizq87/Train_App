package com.bemo.train

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bemo.medicalservices.adapters.StationAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchByLocation : AppCompatActivity(), SearchView.OnQueryTextListener{
    private lateinit var adapter: StationAdapter
    private lateinit var appDatabase: AppDatabase
    private lateinit var stationRecyclerView: RecyclerView
    private lateinit var stationList: ArrayList<Station>
    lateinit var  stations: List<Station>

    private lateinit var mViewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
stations=ArrayList()
        setContentView(R.layout.activity_search_by_location)
        appDatabase=AppDatabase.getInstance(this)
        stationRecyclerView = findViewById(R.id.stations_recyclerview)

        stationList = ArrayList()
        adapter = StationAdapter(this,stationList)
        stationRecyclerView.layoutManager = LinearLayoutManager(this)
        stationRecyclerView.adapter = adapter

       mViewModel=ViewModelProvider(this).get(ViewModel::class.java)


        //appDatabase.trainDao().deleteAll()
       // appDatabase.trainDao().deleteAll2()
      //  appDatabase.trainDao().deleteAll3()
    //    getStationsData()
        adapter.setOnItemClickListener(object : StationAdapter.onItemClickListener{
            override fun onItemClick(pos: Int) {
Toast.makeText(this@SearchByLocation,stations[pos].stationName,Toast.LENGTH_SHORT).show()
            }

        })

    }

     fun click2(view: View) {
         getStationsData()


         val station88 = Station("assuit")
         val station2 = Station("minia")
         val station3 = Station("sohag")
         val station4 = Station("qena")
         val station5 = Station("malwy")

         val station6 = Station("cairo")
         val station7 = Station("alex")
         val station8 = Station("damnhor")
         val station9 = Station("bany swaf")
         GlobalScope.launch(Dispatchers.IO) {
             appDatabase.trainDao().insertStation(station88)
             appDatabase.trainDao().insertStation(station2)
             appDatabase.trainDao().insertStation(station3)
             appDatabase.trainDao().insertStation(station4)
             appDatabase.trainDao().insertStation(station5)
             appDatabase.trainDao().insertStation(station6)
             appDatabase.trainDao().insertStation(station7)
             appDatabase.trainDao().insertStation(station8)

             appDatabase.trainDao().insertStation(station9)
         }
         val train=Train(11164,"star","arr","1d5dkdkdd5")
         val station = Station("mivfsvdfnia")
         val stop = Stops(11164,"mivfsvdfnia","12:15 am")
         GlobalScope.launch(Dispatchers.IO) {
             appDatabase.trainDao().insertTrain(train)
             appDatabase.trainDao().insertStation(station)
             appDatabase.trainDao().insertStop(stop)


         }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        val search = menu?.findItem(R.id.search_menu)
        val searchView =search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled =true
        searchView?.setOnQueryTextListener(this)
    return true
    }
/*
    override fun onQueryTextSubmit(p0: String?): Boolean {
if (p0 != null){
    searchDatabase(p0)
}
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        if (p0 != null){
            searchDatabase(p0)
        }
        return true
    }*/
private fun searchDatabase(query :String){
    val searchQuery= "%$query%"
    stationList.clear()
mViewModel.getStationsByName(searchQuery).observe(this, Observer {list->
    list.let {
            stationList.addAll(it)
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
stationList.clear()
        mViewModel.readAllStationData.observe(this, Observer {
            stationList.addAll(it)
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


