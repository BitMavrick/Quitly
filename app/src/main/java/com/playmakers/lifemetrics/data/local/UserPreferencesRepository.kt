package com.playmakers.lifemetrics.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val TIME_START = stringPreferencesKey("time_start")
        const val TAG = "UserPreferencesRepo"
    }


}