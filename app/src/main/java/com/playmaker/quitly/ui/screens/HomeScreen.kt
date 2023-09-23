package com.playmaker.quitly.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.playmaker.quitly.data.model.ScreenType
import com.playmaker.quitly.ui.stateModel.MainUiState
import com.playmaker.quitly.ui.utils.ContentType
import com.playmaker.quitly.ui.utils.NavigationType


@Composable
fun HomeScreen(
    navigationType: NavigationType,
    contentType: ContentType,
    uiState: MainUiState,
    onTabPressed: (ScreenType) -> Unit,
    onDetailsScreenBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    //
}

@Composable
private fun BottomNavigationBar(
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
private fun NavigationRail(
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

@Preview()
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
            text = "Quitly"
        )

        IconButton(onClick = { /* doSomething() */ }) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "More option"
            )
        }
    }
}

private data class NavigationItemContent(
    val screenType: ScreenType,
    val icon: ImageVector,
    val text: String
)