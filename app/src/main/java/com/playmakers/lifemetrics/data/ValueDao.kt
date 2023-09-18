package com.playmakers.lifemetrics.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ValueDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(value: Value)
    @Query("DELETE from `values`")
    suspend fun delete()

    @Query("SELECT * from `values` WHERE id = :id")
    fun getValue(id: Int): Flow<Value>

    @Query("SELECT * from `values` ORDER BY id ASC")
    fun getAllValues(): Flow<List<Value>>

}