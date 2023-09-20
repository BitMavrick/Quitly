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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.playmakers.lifemetrics.ui.AppViewModelProvider
import com.playmakers.lifemetrics.ui.composables.ActionButtons
import com.playmakers.lifemetrics.ui.composables.NavigationBar
import com.playmakers.lifemetrics.ui.composables.ProgressBar
import com.playmakers.lifemetrics.ui.composables.Quote
import com.playmakers.lifemetrics.ui.composables.TopBar
import com.playmakers.lifemetrics.ui.screens.states.StatesScreen


@Composable
fun LifeMetricsApp(
    homeViewModel: HomeViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    )
){
    HomeScreen(homeViewModel)
}

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
){
    val homeUiState by homeViewModel.uiState.collectAsState()
    val savedTime = homeViewModel.timeState.collectAsState().value

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
                            seconds = homeUiState.seconds,
                            minutes = homeUiState.minutes,
                            hours = homeUiState.hours,
                            days = homeUiState.days,
                            progressValue = homeUiState.progressValue
                        )
                    }
                    ActionButtons(
                        onGaveUpClick = {
                            homeViewModel.gaveUp()
                        },
                        onClearDataClick = {
                            homeViewModel.cleanUp()
                        },
                        onStartClick = {
                            homeViewModel.start()
                        },
                        timeState = savedTime.startTime
                    )
                    Quote()
                }
            }else{
                StatesScreen(
                    innerPadding,
                    homeViewModel
                )
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