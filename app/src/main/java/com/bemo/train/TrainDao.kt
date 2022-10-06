package com.bemo.train

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bemo.train.Entities.Station
import com.bemo.train.Entities.Stops
import com.bemo.train.Entities.Train

@Dao    // data access object
interface TrainDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTrain(train: Train)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStop(stops: Stops)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStation(station: Station)




    @Query("SELECT * FROM Train")
    fun getAllTrains():LiveData<List<Train>>


    @Query("SELECT * FROM Station")
    fun getAllStations():LiveData<List<Station>>



    @Query("SELECT * FROM Train WHERE number LIKE :train_number ")
     fun findByNumber(train_number:String ):LiveData<List<Train>>

    @Query("SELECT * FROM Station WHERE stationName LIKE :station ")
     fun findStationByName(station:String ):LiveData<List<Station>>

    @Delete
    suspend fun delete(train: Train)

    @Query("DELETE FROM Train")
    suspend fun deleteAll()
    @Query("DELETE FROM Station")
    suspend fun deleteAll2()
    @Query("DELETE FROM Stops")
    suspend fun deleteAll3()

   // @Query("UPDATE Train SET start_station=:t_start,time=:t_time WHERE number like :t_tnumber")
 //   suspend fun update(t_start:String,t_time:String,t_tnumber:String)


  //  @Transaction
    //@Query("SELECT * FROM Station WHERE id =:stationID")
   // suspend fun getStationWithStops(stationID: Int):List<StationWithStops>

  /*  @Transaction
    @Query("SELECT * FROM Train WHERE number =:TrainNumber")
    suspend fun getTrainWithStops(TrainNumber: Int):List<TrainWithStops>
*/
}