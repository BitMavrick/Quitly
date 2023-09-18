package com.playmakers.lifemetrics.data

import android.content.Context

interface AppContainer {
    val valuesRepository: ValuesRepository
}
class AppDataContainer(private val context: Context) : AppContainer {
    override val valuesRepository: ValuesRepository by lazy {
        OfflineValuesRepository(LifeMetricsDatabase.getDatabase(context).valueDao())
    }
}