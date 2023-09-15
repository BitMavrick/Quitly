package com.playmakers.lifemetrics.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playmakers.lifemetrics.data.local.UserPreferencesRepository
import com.playmakers.lifemetrics.ui.screens.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class HomeViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    // The UI states
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> =
        userPreferencesRepository.time.map { newTime ->
            UiState(showHomeScreen = false)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState()
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
}