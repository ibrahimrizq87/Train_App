package com.bemo.train.Relations
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.bemo.train.Station
import com.bemo.train.Stops
import com.bemo.train.Train


data class TrainWithStops (
    @Embedded val train: Train,
    @Relation(
        parentColumn = "number",
        entityColumn = "stationName",
        associateBy = Junction(Stops::class)
    )
    val station:  List<Station>

)