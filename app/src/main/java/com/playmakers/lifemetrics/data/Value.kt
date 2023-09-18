package com.playmakers.lifemetrics.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "values")
data class Value(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val period: Long,
)