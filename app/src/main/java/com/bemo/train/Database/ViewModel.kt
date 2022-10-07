package com.bemo.train.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bemo.train.Entities.Station
import com.bemo.train.Entities.Stops
import com.bemo.train.Entities.Train
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application):AndroidViewModel(application) {
      val readAllStationData:LiveData<List<Station>>
    val readAllTrainData:LiveData<List<Train>>

    private  val repository: Repository
    init {
        val databaseDao= AppDatabase.getInstance(application).trainDao()
        repository = Repository(databaseDao)
        readAllStationData = repository.readAllStations
        readAllTrainData = repository.readAllTrains


    }
    fun getStationsByName(name:String):LiveData<List<Station>>{
    return repository.findStationsByName(name)
    }
    fun getTrainByNumber(number:String):LiveData<List<Train>>{
        return repository.findTrainByNumber(number)
    }
    fun getStopsByTrainNumber(number:String):LiveData<List<Stops>>{
        return repository.findStopsByTrainNumber(number)
    }
    fun getStopsByStationName(station:String):LiveData<List<Stops>>{
        return repository.findStopsByStationName(station)
    }



    fun addStations(station: Station){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStation(station)
        }
    }
    fun addTrains(station: Train){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTrain(station)
        }
    }fun addStops(station: Stops){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStop(station)
        }
    }
}