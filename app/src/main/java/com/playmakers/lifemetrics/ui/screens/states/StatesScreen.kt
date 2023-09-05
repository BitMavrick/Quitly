package com.playmakers.lifemetrics.ui.screens.states

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.playmakers.lifemetrics.ui.composables.OverviewCard
import com.playmakers.lifemetrics.ui.theme.LifeMetricsTheme

@Composable
fun StatesScreen(modifier: Modifier = Modifier){

}

@Preview(name = "Action Buttons Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun StatesScreenPreview(){
    LifeMetricsTheme {
        StatesScreen()
    }
}