package com.bemo.train.Relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.bemo.train.Entities.Station
import com.bemo.train.Entities.Stops
import com.bemo.train.Entities.Train

data class StationWithStops (
    @Embedded val station: Station,
    @Relation(
        parentColumn = "stationName",
        entityColumn = "number",
        associateBy = Junction(Stops::class)
    )
    val train: List<Train>

)