package com.playmaker.quitly.ui.stateModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playmaker.quitly.data.model.ScreenType
import com.playmaker.quitly.data.preference.UserPreferencesRepository
import com.playmaker.quitly.ui.utils.DetailType
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MainViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
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
    private var timerJob: Job? = null

    val timeState: StateFlow<PreferenceState> =
        userPreferencesRepository.time.map { newTime ->
            PreferenceState(startTime = newTime)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PreferenceState()
        )
}

data class PreferenceState(
    val startTime: String = "-1",
)