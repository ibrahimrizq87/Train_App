package com.bemo.train

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow


class Repository(private val databaseDao: TrainDao) {
    val readAllStations : LiveData<List<Station>> = databaseDao.getAllStations()

      fun findStationsByName (stationName:String): LiveData<List<Station>> {
    return databaseDao.findStationByName(stationName)
    }


    suspend fun addStation(station: Station){
        databaseDao.insertStation(station)
    }
}