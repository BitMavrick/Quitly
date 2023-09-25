package com.playmaker.quitly.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.playmaker.quitly.data.model.ScreenType
import com.playmaker.quitly.ui.screens.HomeScreen
import com.playmaker.quitly.ui.stateModel.MainViewModel
import com.playmaker.quitly.ui.utils.ContentType
import com.playmaker.quitly.ui.utils.NavigationType

@Composable
fun QuitlyApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val navigationType: NavigationType
    val contentType: ContentType
    val mainViewModel: MainViewModel = viewModel()
    val uiState = mainViewModel.uiState.collectAsState().value

    when(windowSize){
        WindowWidthSizeClass.Compact ->{
            navigationType = NavigationType.BOTTOM_NAVIGATION
            contentType = ContentType.HOME_ONLY
        }
        WindowWidthSizeClass.Medium ->{
            navigationType = NavigationType.NAVIGATION_RAIL
            contentType = ContentType.HOME_AND_DETAIL
        }
        WindowWidthSizeClass.Expanded ->{
            navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = ContentType.HOME_AND_DETAIL
        }
        else ->{
            navigationType = NavigationType.BOTTOM_NAVIGATION
            contentType = ContentType.HOME_ONLY
        }
    }

    HomeScreen(
        navigationType = navigationType,
        contentType = contentType,
        uiState = uiState,
        onTabPressed = {screenType : ScreenType ->
            mainViewModel.updateCurrentScreenType(screenType = screenType)
        },
        onDetailPress = {},
        onDetailsScreenBackPressed = { },
        modifier = modifier
    )
}