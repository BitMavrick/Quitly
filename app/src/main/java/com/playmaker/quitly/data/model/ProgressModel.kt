package com.playmaker.quitly.data.model

data class Progress(
    val title: String,
    val upcoming: String,
    val startTime: Long,
    val endTime: Long,
    val days: Int
)