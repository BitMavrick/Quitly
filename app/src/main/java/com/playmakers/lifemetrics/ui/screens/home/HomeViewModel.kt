package com.playmakers.lifemetrics.ui.screens.home

import androidx.lifecycle.ViewModel
import com.playmakers.lifemetrics.ui.screens.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    // The UI states
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun navigateHomeScreen(){
        _uiState.update { newState ->
            newState.copy(
                showStateScreen = false,
            )
        }
    }

    fun navigateStateScreen(){
        _uiState.update { newState ->
            newState.copy(
                showStateScreen = true,
            )
        }
    }
}