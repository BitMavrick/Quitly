package com.playmakers.lifemetrics.data

import kotlinx.coroutines.flow.Flow

interface ValuesRepository {
    fun getAllValuesStream(): Flow<List<Value>>

    fun getValueStream(id: Int): Flow<Value?>

    suspend fun insertValue(value: Value)

    suspend fun clearValue()
}