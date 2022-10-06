package com.bemo.train

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =
[
    Train::class ,
    Stops::class ,
    Station::class
], version = 1, exportSchema = true)
abstract class AppDatabase :RoomDatabase(){
    abstract fun trainDao():TrainDao
//     abstract val trainDao:TrainDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) { // this lock makes shower that this function is handled by one thread
                // is locked in one thread, because we do not want two instance to be created be two threads
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Database_name"
                ).build().also {
                    INSTANCE = it
                }

            }
            /*fun getDatabase(context: Context):AppDatabase{
            val tempInstance= INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE=instance
                return instance
            }
        }*/
        }
    }}