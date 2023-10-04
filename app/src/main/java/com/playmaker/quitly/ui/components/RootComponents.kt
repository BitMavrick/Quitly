package com.playmaker.quitly.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.playmaker.quitly.R.dimen.topbar_padding_vertical
import com.playmaker.quitly.data.model.ScreenType
import com.playmaker.quitly.ui.stateModel.MainUiState
import com.playmaker.quitly.ui.utils.DetailType
import com.playmaker.quitly.ui.utils.NavigationType

@Composable
fun HomeOnlyContent(
    uiState: MainUiState,
    navigationType: NavigationType,
    onDetailPress: ((DetailType) -> Unit),
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier,
    ){

        if(navigationType == NavigationType.BOTTOM_NAVIGATION){
            item {
                AppHomeTopBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(topbar_padding_vertical))
                )
            }
        }

        if(uiState.currentScreenType == ScreenType.HOME) item {
            Column(
                modifier.padding(horizontal = 16.dp)
            ) {
                TimeCounter()
            }

            if(navigationType == NavigationType.BOTTOM_NAVIGATION){
                Column(
                    modifier.padding(top = 16.dp, start = 16.dp, bottom = 16.dp, end = 16.dp)
                ) {
                    ProgressHistory(onDetailPress = onDetailPress)
                }
            }
        } else{
            item {
                Column(
                    modifier.padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
                ) {
                    OverviewCard()
                    ProgressCard()
                    if(navigationType == NavigationType.BOTTOM_NAVIGATION){
                        ScoreBoard(onDetailPress = onDetailPress)
                    }
                }
            }
        }
    }
}

@Composable
fun HomeAndDetailContent(
    uiState: MainUiState,
    navigationType: NavigationType,
    onDetailPress: (DetailType) -> Unit
){
    if(navigationType == NavigationType.NAVIGATION_RAIL){
        AppHomeTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(topbar_padding_vertical))
        )
    }

    if(uiState.currentScreenType == ScreenType.HOME){
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            HomeOnlyContent(
                uiState = uiState,
                onDetailPress = onDetailPress,
                navigationType = navigationType,
                modifier = Modifier.weight(1f)
            )

            RootDetailComponents(
                uiState = uiState,
                navigationType = navigationType,
                onBackPressed = {},
                modifier = Modifier.weight(1f)
            )
        }
    }else{
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            HomeOnlyContent(
                uiState = uiState,
                onDetailPress = onDetailPress,
                navigationType = navigationType,
                modifier = Modifier.weight(1f)
            )

            RootDetailComponents(
                uiState = uiState,
                navigationType = navigationType,
                onBackPressed = {},
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun AppHomeTopBar(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ){
        Text(
            text = "QUITLY",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = 16.dp)
        )

        IconButton(
            onClick = { }
        ) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "More option",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}