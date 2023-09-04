package com.playmakers.lifemetrics.ui.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.playmakers.lifemetrics.ui.composables.NavigationBar
import com.playmakers.lifemetrics.ui.composables.TopBar
import com.playmakers.lifemetrics.ui.theme.LifeMetricsTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(){
    Scaffold(
        topBar = {
            TopBar()
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier.consumeWindowInsets(innerPadding),
            ) {

            }
        },
        bottomBar = {
            NavigationBar()
        },
    )
}

@Preview(name = "Action Buttons Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview(){
    LifeMetricsTheme {
        HomeScreen()
    }
}