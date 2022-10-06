package com.bemo.train

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Train (
    @PrimaryKey(autoGenerate = false)
    val number:Int,
    val startStation:String,
    val arriveStation:String,
    val time:String
)
