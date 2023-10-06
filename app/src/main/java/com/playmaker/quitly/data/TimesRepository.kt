package com.playmaker.quitly.data

import com.playmaker.quitly.data.model.Time
import kotlinx.coroutines.flow.Flow

interface TimesRepository {
    fun getAllValuesStream(): Flow<List<Time>>

    fun getValueStream(id: Int): Flow<Time?>

    suspend fun insertValue(time: Time)

    suspend fun clearValue()
}