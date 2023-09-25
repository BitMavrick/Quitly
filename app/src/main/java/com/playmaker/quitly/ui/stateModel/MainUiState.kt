package com.playmaker.quitly.ui.stateModel

import com.playmaker.quitly.data.model.ScreenType
import com.playmaker.quitly.ui.utils.DetailType

data class MainUiState (
    val currentScreenType: ScreenType = ScreenType.HOME,
    val currentDetailType: DetailType = DetailType.NONE,
    val isShowingHomepage: Boolean = true,
)