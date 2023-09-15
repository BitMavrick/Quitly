package com.playmakers.lifemetrics

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.playmakers.lifemetrics.data.local.UserPreferencesRepository


private const val TIME_PREFERENCE_NAME = "time_preference"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = TIME_PREFERENCE_NAME
)

class LifeMetricsApplication : Application() {
    lateinit var userPreferencesRepository: UserPreferencesRepository
    override fun onCreate() {
        super.onCreate()
        userPreferencesRepository = UserPreferencesRepository(dataStore)
    }
}