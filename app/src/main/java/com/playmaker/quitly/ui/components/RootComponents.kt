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
import com.playmaker.quitly.ui.utils.NavigationType

@Composable
fun HomeOnlyContent(
    uiState: MainUiState,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier,
    ){
        item {
            AppHomeTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(topbar_padding_vertical))
            )
        }

        if(uiState.currentScreenType == ScreenType.Home){
            item {
                Column(
                    modifier.padding(top = 8.dp, start = 16.dp, bottom = 16.dp, end = 16.dp)
                ) {
                    RandomQuote()
                }

                Column(
                    modifier.padding(horizontal = 16.dp)
                ) {
                    TimeCounter()
                }

                Column(
                    modifier.padding(top = 16.dp, start = 16.dp, bottom = 16.dp, end = 16.dp)
                ) {
                    ProgressHistory()
                }
            }
        }else{
            item {
                Column(
                    modifier.padding(horizontal = 16.dp)
                ) {
                    Text(text = "This is the Rank screen")
                }
            }
        }
    }
}

@Composable
fun HomeAndDetailContent(
    uiState: MainUiState,
    navigationType: NavigationType,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier,
    ){
        if(navigationType == NavigationType.NAVIGATION_RAIL){
            item {
                AppHomeTopBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(topbar_padding_vertical))
                )
            }
        }

        if(uiState.currentScreenType == ScreenType.Home){
            item {
                Text(text = "This is the Home and detail screen")
            }
        }else{
            item {
                Text(text = "This is the Rank and detail screen")
            }
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