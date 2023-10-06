package com.playmaker.quitly.data

import android.content.Context
import com.playmaker.quitly.data.room.LocalTimesRepository
import com.playmaker.quitly.data.room.QuitlyDatabase

interface AppContainer {
    val timesRepository: TimesRepository
}
class AppDataContainer(private val context: Context): AppContainer{
    override val timesRepository: TimesRepository by lazy {
        LocalTimesRepository(QuitlyDatabase.getDatabase(context).timeDao())
    }
}