package com.playmakers.lifemetrics.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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


@Composable
fun LifeMetricsApp(
    homeViewModel: HomeViewModel = viewModel(
        factory = HomeViewModel.Factory
    )
){
    HomeScreen(homeViewModel)
}


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
){
    val homeUiState by homeViewModel.uiState.collectAsState()
    val timeState = homeViewModel.timeState.collectAsState().value

    Scaffold(
        topBar = {
            TopBar()
        },
        content = { innerPadding ->
            if(homeUiState.showHomeScreen){
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Box(Modifier.padding(18.dp)){
                        ProgressBar(
                            seconds = homeUiState.seconds.toString(),
                            minutes = homeUiState.minutes.toString(),
                            hours = homeUiState.hours.toString(),
                            days = homeUiState.days.toString(),
                            progressValue = homeUiState.progressValue
                        )
                    }
                    ActionButtons(
                        onGaveUpClick = {
                            homeViewModel.startTimer(timeState.startTime.toLong())
                        },
                        onClearDataClick = {
                            homeViewModel.saveTime()
                            homeViewModel.startTimer(timeState.startTime.toLong())
                        }
                    )
                    Quote()
                }
            }else{
                StatesScreen(innerPadding)
            }
        },
        bottomBar = {
            NavigationBar(
                onHomeButtonClicked = { homeViewModel.navigateHomeScreen() },
                onStateButtonClicked = { homeViewModel.navigateStateScreen() },
                selectedValue = homeUiState.showHomeScreen
            )
        }
    )
}

@Preview
@Composable
fun HomeScreenPreview(
    homeViewModel: HomeViewModel = viewModel(
        factory = HomeViewModel.Factory
    )
){
    LifeMetricsTheme {
        HomeScreen(homeViewModel)
    }
}