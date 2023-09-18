package com.playmakers.lifemetrics.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Value::class], version = 1, exportSchema = false)
abstract class LifeMetricsDatabase : RoomDatabase() {
    abstract fun valueDao(): ValueDao

    companion object {
        @Volatile
        private var Instance: LifeMetricsDatabase? = null
        fun getDatabase(context: Context): LifeMetricsDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, LifeMetricsDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}