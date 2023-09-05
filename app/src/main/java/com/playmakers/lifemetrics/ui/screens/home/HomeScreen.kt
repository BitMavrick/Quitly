package com.playmakers.lifemetrics.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.playmakers.lifemetrics.ui.composables.ActionButtons
import com.playmakers.lifemetrics.ui.composables.NavigationBar
import com.playmakers.lifemetrics.ui.composables.ProgressBar
import com.playmakers.lifemetrics.ui.composables.Quote
import com.playmakers.lifemetrics.ui.composables.TopBar
import com.playmakers.lifemetrics.ui.screens.states.StatesScreen
import com.playmakers.lifemetrics.ui.theme.LifeMetricsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel()
){
    Scaffold(
        topBar = {
            TopBar()
        },
        content = { innerPadding ->
            if(!homeViewModel.isShowStateScreen.value){
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
            }else{
                StatesScreen(innerPadding)
            }
        },
        bottomBar = {
            NavigationBar()
        }
    )
}

@Preview
@Composable
fun HomeScreenPreview(){
    LifeMetricsTheme {
        HomeScreen()
    }
}