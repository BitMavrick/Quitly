package com.playmakers.lifemetrics.data

import kotlinx.coroutines.flow.Flow

class OfflineValuesRepository(private val valueDao: ValueDao) : ValuesRepository {
    override fun getAllValuesStream(): Flow<List<Value>> {
        return valueDao.getAllValues()
    }

    override fun getValueStream(id: Int): Flow<Value?> {
        return valueDao.getValue(id)
    }

    override suspend fun insertValue(value: Value) {
        return valueDao.insert(value)
    }

    override suspend fun deleteValue(value: Value) {
        return valueDao.delete(value)
    }
}