package com.playmakers.lifemetrics.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DateTimePreferences(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("dateTime_preferences")
        val DATE_TIME_KEY = stringPreferencesKey("dateTime_key")
    }

    val dateTimeFlow: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[DATE_TIME_KEY]
    }

    suspend fun saveDateTime(dateTime: String) {
        context.dataStore.edit { preferences ->
            preferences[DATE_TIME_KEY] = dateTime
        }
    }
}