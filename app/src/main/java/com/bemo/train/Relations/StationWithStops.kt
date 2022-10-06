package com.bemo.train.Relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.bemo.train.Station
import com.bemo.train.Stops
import com.bemo.train.Train

data class StationWithStops (
    @Embedded val station: Station,
    @Relation(
        parentColumn = "stationName",
        entityColumn = "number",
        associateBy = Junction(Stops::class)
    )
    val train: List<Train>

)