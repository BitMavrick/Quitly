package com.playmakers.lifemetrics.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.playmakers.lifemetrics.LifeMetricsApplication
import com.playmakers.lifemetrics.data.local.UserPreferencesRepository
import com.playmakers.lifemetrics.ui.screens.UiState
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
class HomeViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    // The UI states
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    // Timing
    private var seconds by mutableStateOf("00")
    private var minutes by mutableStateOf("00")
    private var hours by mutableStateOf("00")
    private var days by mutableStateOf("0")
    private var progress by mutableFloatStateOf(0.0f)

    private var timerJob: Job? = null


    val timeState: StateFlow<PreferenceState> =
        userPreferencesRepository.time.map { newTime ->
            PreferenceState(startTime = newTime)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PreferenceState()
        )


    fun saveTime(){
        viewModelScope.launch {
            userPreferencesRepository.saveTimePreference(System.currentTimeMillis().toString()) // Actually its a long value
        }
    }

    fun resetTime(){
        viewModelScope.launch {
            userPreferencesRepository.saveTimePreference("-1")
        }
    }

    fun navigateHomeScreen(){
        _uiState.update { newState ->
            newState.copy(
                showHomeScreen = true,
            )
        }
    }

    fun navigateStateScreen(){
        _uiState.update { newState ->
            newState.copy(
                showHomeScreen = false,
            )
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun startTimer(startTime: Long) {
        timerJob?.cancel() // Cancel the previous timer job if it exists
        timerJob = GlobalScope.launch {
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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as LifeMetricsApplication)
                HomeViewModel(application.userPreferencesRepository)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel() // Cancel the timer job when the ViewModel is cleared
    }

    // Create a init block here

    init {
        GlobalScope.launch{
            startTimer(12362536)
        }
    }
}

data class PreferenceState(
    val startTime: String = "-1",
)