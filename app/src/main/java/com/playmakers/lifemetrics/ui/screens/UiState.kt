package com.playmakers.lifemetrics.ui.screens

import com.playmakers.lifemetrics.data.Value

data class UiState (
    val showHomeScreen: Boolean = true,
    val seconds: String = "00",
    val minutes: String = "00",
    val hours: String = "00",
    val days: String = "0",
    val runningTimeSeconds: Long = 0L,
    val progressValue : Float = 0.0f
)
fun UiState.toValue(): Value = Value(
    id = 0,
    period = runningTimeSeconds
)