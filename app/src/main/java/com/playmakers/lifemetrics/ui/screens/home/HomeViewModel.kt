package com.playmakers.lifemetrics.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playmakers.lifemetrics.data.ValuesRepository
import com.playmakers.lifemetrics.data.local.UserPreferencesRepository
import com.playmakers.lifemetrics.ui.screens.UiState
import com.playmakers.lifemetrics.ui.screens.toValue
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
class HomeViewModel(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val valuesRepository: ValuesRepository
) : ViewModel() {

    // The UI states
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null


    val timeState: StateFlow<PreferenceState> =
        userPreferencesRepository.time.map { newTime ->
            PreferenceState(startTime = newTime)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PreferenceState()
        )

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

    fun start(){
        resetTime()
        saveTime()
        startTimer(timeState.value.startTime.toLong())
    }

    fun gaveUp(){
        start()
        viewModelScope.launch {
            saveTimeInDatabase()
        }
    }

    fun cleanUp() {
        timerJob?.cancel()
        resetTime()
        _uiState.update { newState ->
            newState.copy(
                days = "0",
                seconds = "00",
                minutes = "00",
                hours = "00",
                progressValue = 0.0f
            )
        }
    }

    private fun saveTime(){
        viewModelScope.launch {
            userPreferencesRepository.saveTimePreference(System.currentTimeMillis().toString()) // Actually its a long value
        }

    }

    private fun resetTime(){
        viewModelScope.launch {
            userPreferencesRepository.saveTimePreference("-1")
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun startTimer(startTime: Long) {
        timerJob?.cancel() // Cancel the previous timer job if it exists
        val time: Long = if(startTime == -1L){
            System.currentTimeMillis()
        }else{
            startTime
        }

        timerJob = GlobalScope.launch {
            while (true) {
                val currentTimeMillis = System.currentTimeMillis()
                val elapsedMillis = currentTimeMillis - time
                updateTimeStates(elapsedMillis / 1000)
                delay(1000)
            }
        }
    }

    private fun updateTimeStates(totalSeconds: Long) {
        val secondsInADay = 24 * 60 * 60 // 24 hours * 60 minutes * 60 seconds

        // Calculate progress value as a fraction of the day's seconds
        val progressValue = (totalSeconds % secondsInADay).toFloat() / secondsInADay

        _uiState.update { newState ->
            newState.copy(
                days = (totalSeconds / secondsInADay).toString(),
                seconds = (totalSeconds % 60).toString().padStart(2, '0'),
                minutes = ((totalSeconds % 3600) / 60).toString().padStart(2, '0'),
                hours = ((totalSeconds % 86400) / 3600).toString().padStart(2, '0'),
                progressValue = progressValue,
                runningTimeSeconds = totalSeconds
            )
        }
    }

    private suspend fun saveTimeInDatabase(){
        valuesRepository.insertValue(uiState.value.toValue())
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel() // Cancel the timer job when the ViewModel is cleared
    }


    init {
        viewModelScope.launch {
            userPreferencesRepository.time.distinctUntilChanged().collect { startTime ->
                if (startTime != "-1") {
                    startTimer(timeState.value.startTime.toLong())
                }
            }
        }
    }
}

data class PreferenceState(
    val startTime: String = "-1",
)

