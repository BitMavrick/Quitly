package com.playmaker.quitly.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "times")
data class Time (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val period: Long,
)