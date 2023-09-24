package com.playmaker.quitly.ui.stateModel

import androidx.lifecycle.ViewModel
import com.playmaker.quitly.data.model.ScreenType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel(){

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState

    fun updateCurrentScreenType(screenType: ScreenType){
        _uiState.update {
            it.copy(
                currentScreenType = screenType
            )
        }
    }
}