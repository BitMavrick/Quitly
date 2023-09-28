package com.playmaker.quitly.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.playmaker.quitly.ui.stateModel.MainUiState
import com.playmaker.quitly.ui.utils.DetailType
import com.playmaker.quitly.ui.utils.NavigationType

@Composable
fun RootDetailComponents(
    uiState: MainUiState,
    navigationType: NavigationType,
    onBackPressed: () -> Unit,
) {
    BackHandler {
        onBackPressed()
    }

    LazyColumn {
        if(navigationType == NavigationType.BOTTOM_NAVIGATION){
            item {
                AppDetailTopBar(
                    uiState,
                    onBackPressed
                )
            }
        }

        if(uiState.currentDetailType == DetailType.HOME_DETAIL){
            item {
                Column(
                    Modifier.padding(16.dp)
                ) {
                    ProgressCard()
                }
            }
        }else{
            item{
                Column(
                    Modifier.padding(16.dp)
                ) {
                    AchievementCard()
                }
            }
        }
    }
}

@Composable
private fun AppDetailTopBar(
    uiState: MainUiState,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth().padding(top = 8.dp),
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton(
                onClick = { onBackPressed() }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "More option",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            if(uiState.currentDetailType == DetailType.HOME_DETAIL){
                Text(
                    text = "PROGRESS HISTORY",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
            }else{
                Text(
                    text = "ACHIEVEMENTS",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }

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


@Composable
fun ProgressCard(){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    Icons.Outlined.Lightbulb,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null,
                )
                Text(
                    text = "Progress Card",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "- This is the progress card",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun AchievementCard(){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    Icons.Outlined.Lightbulb,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null,
                )
                Text(
                    text = "Achievement Card",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "- This is the achievement card",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}