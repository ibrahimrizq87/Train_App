package com.bemo.train.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Train (
    @PrimaryKey(autoGenerate = false)
    val number:String,
    val startStation:String,
    val arriveStation:String,
    val startTime:String,
    val arriveTime:String
    ,val trainType:String
)
