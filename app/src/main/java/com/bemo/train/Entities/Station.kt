package com.bemo.train.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Station (
    @PrimaryKey(autoGenerate = false)
    //val id:Int,
    val stationName:String,

)