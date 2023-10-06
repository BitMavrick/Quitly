package com.playmaker.quitly

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.playmaker.quitly.data.AppContainer
import com.playmaker.quitly.data.AppDataContainer
import com.playmaker.quitly.data.preference.UserPreferencesRepository

private const val TIME_PREFERENCE_NAME = "time_preference"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = TIME_PREFERENCE_NAME
)
class QuitlyApplication: Application() {
    lateinit var userPreferencesRepository: UserPreferencesRepository
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        userPreferencesRepository = UserPreferencesRepository(dataStore)
        container = AppDataContainer(this)
    }
}