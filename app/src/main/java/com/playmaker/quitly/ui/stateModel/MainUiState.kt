package com.playmaker.quitly.ui.stateModel

import com.playmaker.quitly.data.model.ScreenType

data class MainUiState (
    val currentScreenType: ScreenType = ScreenType.Home,
    val isShowingHomepage: Boolean = true
)