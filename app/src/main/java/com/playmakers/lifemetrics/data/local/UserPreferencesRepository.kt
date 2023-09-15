package com.playmakers.lifemetrics.data.local

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object{
        val TIME_START = stringPreferencesKey("time_start")
        const val TAG = "UserPreferencesRepo"
    }

    suspend fun saveTimePreference(time: String){
        dataStore.edit { preferences->
            preferences[TIME_START] = time
        }
    }

    val time: Flow<String> = dataStore.data
        .catch {
            if(it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[TIME_START] ?: "Hello World"
        }
}