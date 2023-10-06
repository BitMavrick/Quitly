package com.playmaker.quitly.data.room

import androidx.room.Dao
import com.playmaker.quitly.data.model.Time
import androidx.room.OnConflictStrategy
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TimeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(time: Time)
    @Query("DELETE from `times`")
    suspend fun delete()

    @Query("SELECT * from `times` WHERE id = :id")
    fun getValue(id: Int): Flow<Time>

    @Query("SELECT * from `times` ORDER BY id ASC")
    fun getAllValues(): Flow<List<Time>>
}