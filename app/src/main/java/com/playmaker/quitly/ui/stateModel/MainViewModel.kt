package com.playmaker.quitly.ui.stateModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playmaker.quitly.data.model.ScreenType
import com.playmaker.quitly.data.preference.UserPreferencesRepository
import com.playmaker.quitly.ui.utils.DetailType
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    //private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel(){

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState

    // UI related functions
    fun updateCurrentScreenType(screenType: ScreenType){
        _uiState.update {
            it.copy(
                currentScreenType = screenType
            )
        }
    }

    fun setDetailScreenType(detailType: DetailType){
        _uiState.update {
            it.copy(
                currentDetailType = detailType,
                isShowingHomepage = false,
            )
        }
    }

    fun resetDetailScreenType(){
        _uiState.update {
            it.copy(
                currentDetailType = DetailType.NONE,
                isShowingHomepage = true
            )
        }
    }

    // Backend related functions and systems
    //private var timerJob: Job? = null
/*
    val timeState: StateFlow<PreferenceState> =
        userPreferencesRepository.time.map { newTime ->
            PreferenceState(startTime = newTime)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PreferenceState()
        )

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

 */


}

data class PreferenceState(
    val startTime: String = "-1",
)