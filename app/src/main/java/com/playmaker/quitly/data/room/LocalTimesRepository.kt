package com.playmaker.quitly.data.room

import com.playmaker.quitly.data.TimesRepository
import com.playmaker.quitly.data.model.Time
import kotlinx.coroutines.flow.Flow

class LocalTimesRepository(private val timeDao: TimeDao) : TimesRepository {
    override fun getAllValuesStream(): Flow<List<Time>> {
        return timeDao.getAllValues()
    }

    override fun getValueStream(id: Int): Flow<Time?> {
        return timeDao.getValue(id)
    }

    override suspend fun insertValue(time: Time) {
        return timeDao.insert(time)
    }

    override suspend fun clearValue() {
        return timeDao.delete()
    }
}