package com.example.busschedule.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import android.content.Context

@Database(entities = arrayOf(BusSchedule::class), version = 1)
abstract class BusDatabase: RoomDatabase()  {
    abstract fun busScheduleDao() : BusScheduleDao

    companion object {
        @Volatile
        private var INSTANCE: BusDatabase? = null
        fun getDatabase(context: Context): BusDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, BusDatabase::class.java, "bus_database")
                    .createFromAsset("database/bus_schedule.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it
                    }
            }
        }
    }
}