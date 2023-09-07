package com.playmakers.lifemetrics.ui.screens.states

import android.content.res.Configuration
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playmakers.lifemetrics.ui.composables.AchievementCard
import com.playmakers.lifemetrics.ui.composables.GraphCard
import com.playmakers.lifemetrics.ui.composables.OverviewCard
import com.playmakers.lifemetrics.ui.theme.LifeMetricsTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun StatesScreen(paddingValues: PaddingValues){
    LazyColumn(
        modifier = Modifier
            .consumeWindowInsets(paddingValues)
            .padding(16.dp),
        contentPadding = paddingValues
    ){
        item{
            OverviewCard()
        }

        item{
            GraphCard()
        }

        item{
            AchievementCard()
        }
    }
}

@Preview(name = "Action Buttons Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun StatesScreenPreview(){
    LifeMetricsTheme {
        StatesScreen(paddingValues = PaddingValues())
    }
}