package com.playmaker.quitly.data

import com.playmaker.quitly.data.model.Time
import kotlinx.coroutines.flow.Flow

interface TimesRepository {
    fun getAllTimesStream(): Flow<List<Time>>

    fun getTimeStream(id: Int): Flow<Time?>

    suspend fun insertTime(time: Time)

    suspend fun clearTime()
}