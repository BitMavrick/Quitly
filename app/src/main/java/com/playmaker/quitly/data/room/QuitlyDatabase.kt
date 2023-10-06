package com.playmaker.quitly.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.playmaker.quitly.data.model.Time

@Database(entities = [Time::class], version = 1, exportSchema = false)
abstract class QuitlyDatabase: RoomDatabase() {
    abstract fun timeDao(): TimeDao
    companion object {
        @Volatile
        private var Instance: QuitlyDatabase? = null
        fun getDatabase(context: Context): QuitlyDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, QuitlyDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}