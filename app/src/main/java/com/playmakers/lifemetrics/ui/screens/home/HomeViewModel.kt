package com.playmakers.lifemetrics.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.playmakers.lifemetrics.LifeMetricsApplication
import com.playmakers.lifemetrics.data.local.UserPreferencesRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    // State for showing different screens
    val isShowStateScreen = mutableStateOf(false)

    // Timing
    var seconds by mutableStateOf("00")
    var minutes by mutableStateOf("00")
    var hours by mutableStateOf("00")
    var days by mutableStateOf("0")
    var progress by mutableFloatStateOf(0.0f)

    private var timerJob: Job? = null

    fun showStateScreen() {
        isShowStateScreen.value = true
    }

    fun showHomeScreen() {
        isShowStateScreen.value = false
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun startTimer() {
        timerJob?.cancel() // Cancel the previous timer job if it exists
        timerJob = GlobalScope.launch {
            val startTime = System.currentTimeMillis()
            while (true) {
                val currentTimeMillis = System.currentTimeMillis()
                val elapsedMillis = currentTimeMillis - startTime
                updateTimeStates(elapsedMillis / 1000)
                delay(1000)
            }
        }
    }

    private fun updateTimeStates(totalSeconds: Long) {
        val secondsInADay = 24 * 60 * 60 // 24 hours * 60 minutes * 60 seconds

        // Calculate progress value as a fraction of the day's seconds
        val progressValue = (totalSeconds % secondsInADay).toFloat() / secondsInADay

        // Update the mutableStateOf variables
        days = (totalSeconds / secondsInADay).toString()
        seconds = (totalSeconds % 60).toString().padStart(2, '0')
        minutes = ((totalSeconds % 3600) / 60).toString().padStart(2, '0')
        hours = ((totalSeconds % 86400) / 3600).toString().padStart(2, '0')
        progress = progressValue
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel() // Cancel the timer job when the ViewModel is cleared
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as LifeMetricsApplication)
                HomeViewModel(application.userPreferencesRepository)
            }
        }
    }
}