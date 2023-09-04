package com.playmakers.lifemetrics.ui.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playmakers.lifemetrics.ui.composables.ActionButtons
import com.playmakers.lifemetrics.ui.composables.NavigationBar
import com.playmakers.lifemetrics.ui.composables.ProgressBar
import com.playmakers.lifemetrics.ui.composables.Quote
import com.playmakers.lifemetrics.ui.composables.TopBar
import com.playmakers.lifemetrics.ui.theme.LifeMetricsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    Scaffold(
        topBar = {
            TopBar()
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(Modifier.padding(18.dp)){
                    ProgressBar()
                }
                ActionButtons()
                Quote()
            }
        },
        bottomBar = {
            NavigationBar()
        }
    )
}

@Preview(name = "Action Buttons Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview(){
    LifeMetricsTheme {
        HomeScreen()
    }
}