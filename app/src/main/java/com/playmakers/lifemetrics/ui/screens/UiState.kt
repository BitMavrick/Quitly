package com.playmakers.lifemetrics.ui.screens

data class UiState (
    val showHomeScreen: Boolean = true,
    val seconds: String = "00",
    val minutes: String = "00",
    val hours: String = "00",
    val days: String = "0",
    val runningTimeMillis: Long = 0L,
    val progressValue : Float = 0.0f
)