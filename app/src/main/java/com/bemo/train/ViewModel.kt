package com.bemo.train
import kotlinx.coroutines.flow.Flow

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application):AndroidViewModel(application) {
      val readAllStationData:LiveData<List<Station>>
    private  val repository:Repository
    init {
        val databaseDao= AppDatabase.getInstance(application).trainDao()
        repository = Repository(databaseDao)
        readAllStationData = repository.readAllStations

    }
    fun getStationsByName(name:String):LiveData<List<Station>>{
    return repository.findStationsByName(name)
    }

    fun addStations(station: Station){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStation(station)
        }
    }
}