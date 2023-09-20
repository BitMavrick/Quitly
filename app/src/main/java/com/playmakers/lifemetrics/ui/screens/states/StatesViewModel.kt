package com.playmakers.lifemetrics.ui.screens.states

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playmakers.lifemetrics.data.Value
import com.playmakers.lifemetrics.data.ValuesRepository
import com.playmakers.lifemetrics.ui.screens.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class StatesViewModel(
    valuesRepository: ValuesRepository
) : ViewModel() {

    // The UI states


    val statesUiState : StateFlow<StatesUiState> =
        valuesRepository.getAllValuesStream().map { StatesUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = StatesUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}

data class StatesUiState(
    val valueList: List<Value> = listOf()
)