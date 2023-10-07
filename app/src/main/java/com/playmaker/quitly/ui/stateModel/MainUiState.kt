package com.playmaker.quitly.ui.stateModel

import com.playmaker.quitly.data.model.ScreenType
import com.playmaker.quitly.data.model.Time
import com.playmaker.quitly.ui.utils.DetailType

data class MainUiState (
    // UI related
    val currentScreenType: ScreenType = ScreenType.HOME,
    val currentDetailType: DetailType = DetailType.NONE,
    val isShowingHomepage: Boolean = true,

    // Backend related
    val seconds: String = "00",
    val minutes: String = "00",
    val hours: String = "00",
    val days: String = "0",
    val runningTimeSeconds: Long = 0L,
    val progressValue : Float = 0.0f
)

fun MainUiState.toData(): Time = Time(
    id = 0,
    period = runningTimeSeconds
)
