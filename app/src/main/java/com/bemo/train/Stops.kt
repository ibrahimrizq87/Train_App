package com.bemo.train
import androidx.room.Entity



@Entity(primaryKeys = ["number","stationName"] )
data class Stops (
    val number:Int,
    val stationName:String
    ,val time:String
)