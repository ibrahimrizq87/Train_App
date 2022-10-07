package com.bemo.train.Entities
import androidx.room.Entity



@Entity(primaryKeys = ["number","stationName"] )
data class Stops (
    val number:String,
    val stationName:String
    ,val time:String
    ,val first_class_cost:String
    ,val second_class_cost:String

)