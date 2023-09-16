package com.playmakers.lifemetrics.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.playmakers.lifemetrics.LifeMetricsApplication
import com.playmakers.lifemetrics.data.local.UserPreferencesRepository
import com.playmakers.lifemetrics.ui.screens.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    // The UI states
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()


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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as LifeMetricsApplication)
                HomeViewModel(application.userPreferencesRepository)
            }
        }
    }

    // Create a init block here
}

data class PreferenceState(
    val startTime: String = "-1",
)