package com.playmaker.quitly.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.playmaker.quitly.R
import com.playmaker.quitly.data.model.Progress
import com.playmaker.quitly.data.model.ScreenType
import com.playmaker.quitly.ui.components.HomeAndDetailContent
import com.playmaker.quitly.ui.components.HomeOnlyContent
import com.playmaker.quitly.ui.components.RootDetailComponents
import com.playmaker.quitly.ui.stateModel.MainUiState
import com.playmaker.quitly.ui.stateModel.StatesUiData
import com.playmaker.quitly.ui.utils.ContentType
import com.playmaker.quitly.ui.utils.DetailType
import com.playmaker.quitly.ui.utils.NavigationType

@Composable
fun HomeScreen(
    navigationType: NavigationType,
    contentType: ContentType,
    uiState: MainUiState,
    statesUiData: StatesUiData,
    progress: Progress,
    timeState: String,
    onStartPress: () -> Unit,
    onGaveUpPress: () -> Unit,
    onClearDataPress: () -> Unit,
    onTabPressed: (ScreenType) -> Unit,
    onDetailPress: (DetailType) -> Unit,
    onDetailsScreenBackPressed: () -> Unit = {},
) {
    val navigationItemContentList = listOf(
        NavigationItemContent(
            screenType = ScreenType.HOME,
            icon = Icons.Filled.Home,
            text = "Home"
        ),
        NavigationItemContent(
            screenType = ScreenType.RANK,
            icon = Icons.Filled.BarChart,
            text = "Ranks"
        ),
    )

    if(navigationType == NavigationType.PERMANENT_NAVIGATION_DRAWER){
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet(Modifier.width(dimensionResource(R.dimen.drawer_width))) {
                    AppNavigationDrawer(
                        selectedDestination = uiState.currentScreenType,
                        onTabPressed = onTabPressed,
                        navigationItemContentList = navigationItemContentList,
                        modifier = Modifier
                            .wrapContentWidth()
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.inverseOnSurface)
                            .padding(dimensionResource(R.dimen.drawer_padding_content))
                    )
                }
            }
        ) {
            AppContent(
                navigationType = navigationType,
                contentType = contentType,
                uiState = uiState,
                progress = progress,
                statesUiData = statesUiData,
                timeState = timeState,
                onStartPress = onStartPress,
                onGaveUpPress = onGaveUpPress,
                onClearDataPress = onClearDataPress,
                onTabPressed = onTabPressed,
                onDetailPress = onDetailPress,
                navigationItemContentList = navigationItemContentList
            )
        }
    }else{
        if(uiState.isShowingHomepage){
            AppContent(
                navigationType = navigationType,
                contentType = contentType,
                uiState = uiState,
                statesUiData = statesUiData,
                progress = progress,
                timeState = timeState,
                onStartPress = onStartPress,
                onGaveUpPress = onGaveUpPress,
                onClearDataPress = onClearDataPress,
                onTabPressed = onTabPressed,
                onDetailPress = onDetailPress,
                navigationItemContentList = navigationItemContentList
            )
        }else{
            RootDetailComponents(
                uiState = uiState,
                statesUiData = statesUiData,
                navigationType = navigationType,
                onBackPressed = onDetailsScreenBackPressed
            )
        }
    }
}

@Composable
private fun AppContent(
    navigationType: NavigationType,
    contentType: ContentType,
    uiState: MainUiState,
    statesUiData : StatesUiData,
    progress: Progress,
    timeState: String,
    onStartPress: () -> Unit,
    onGaveUpPress: () -> Unit,
    onClearDataPress: () -> Unit,
    onTabPressed: (ScreenType) -> Unit,
    onDetailPress: (DetailType) -> Unit,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
){
    Box(modifier = modifier){
        Row(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(visible = navigationType == NavigationType.NAVIGATION_RAIL) {
                AppNavigationRail(
                    currentTab = uiState.currentScreenType,
                    onTabPressed = onTabPressed,
                    navigationItemContentList = navigationItemContentList
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
            ) {
                if(contentType == ContentType.HOME_AND_DETAIL){
                    HomeAndDetailContent(
                        uiState = uiState,
                        statesUiData = statesUiData,
                        timeState = timeState,
                        progress = progress,
                        onStartPress = onStartPress,
                        onGaveUpPress = onGaveUpPress,
                        onClearDataPress = onClearDataPress,
                        navigationType = navigationType,
                        onDetailPress = onDetailPress,
                    )
                }else{
                    HomeOnlyContent(
                        uiState = uiState,
                        timeState = timeState,
                        onStartPress = onStartPress,
                        progress = progress,
                        onGaveUpPress = onGaveUpPress,
                        onClearDataPress = onClearDataPress,
                        navigationType = navigationType,
                        onDetailPress = onDetailPress,
                        modifier = Modifier.weight(1f)
                    )
                }

                AnimatedVisibility(visible = navigationType == NavigationType.BOTTOM_NAVIGATION && uiState.currentDetailType == DetailType.NONE) {
                    AppBottomNavigationBar(
                        currentTab = uiState.currentScreenType,
                        onTabPressed = onTabPressed,
                        navigationItemContentList = navigationItemContentList,
                    )
                }
            }
        }
    }
}

@Composable
private fun AppBottomNavigationBar(
    currentTab: ScreenType,
    onTabPressed: ((ScreenType) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationBarItem(
                selected = currentTab == navItem.screenType,
                onClick = { onTabPressed(navItem.screenType) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text
                    )
                }
            )
        }
    }
}

@Composable
private fun AppNavigationRail(
    currentTab: ScreenType,
    onTabPressed: ((ScreenType) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    NavigationRail(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationRailItem(
                selected = currentTab == navItem.screenType,
                onClick = { onTabPressed(navItem.screenType) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text
                    )
                }
            )
        }
    }
}

@Composable
private fun AppNavigationDrawer(
    selectedDestination: ScreenType,
    onTabPressed: ((ScreenType) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier){
        NavigationDrawerHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )
        for (navItem in navigationItemContentList) {
            NavigationDrawerItem(
                selected = selectedDestination == navItem.screenType,
                label = {
                    Text(
                        text = navItem.text,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent
                ),
                onClick = { onTabPressed(navItem.screenType) }
            )
        }
    }
}

@Composable
private fun NavigationDrawerHeader(
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = "QUITLY",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
        )

        IconButton(onClick = { /* doSomething() */ }) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "More option",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

private data class NavigationItemContent(
    val screenType: ScreenType,
    val icon: ImageVector,
    val text: String
)