package com.playmakers.lifemetrics.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.time.Duration
import java.util.Timer
import kotlin.concurrent.fixedRateTimer

class HomeViewModel : ViewModel() {
    val isShowStateScreen = mutableStateOf(false)
    private var time: Duration = Duration.ZERO
    private lateinit var timer: Timer

    // Timing
    var seconds by mutableStateOf("00")
    var minutes by mutableStateOf("00")
    var hours by mutableStateOf("00")

    fun showStateScreen(){
        isShowStateScreen.value = true
    }

    fun showHomeScreen(){
        isShowStateScreen.value = false
    }

    fun startTime(){
        timer = fixedRateTimer(initialDelay = 1000L, period = 1000L){
            time = time.plus(Duration.ofSeconds(1))
            updateTimeStates()
        }
    }

    private fun updateTimeStates(){
        val totalSeconds = time.seconds

        val hoursValue = totalSeconds / 3600
        val minutesValue = (totalSeconds % 3600) / 60
        val secondsValue = totalSeconds % 60

        // Update the mutableStateOf variables
        seconds = secondsValue.toString().padStart(2, '0')
        minutes = minutesValue.toString().padStart(2, '0')
        hours = hoursValue.toString().padStart(2, '0')
    }
}