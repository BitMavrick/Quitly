package com.playmakers.lifemetrics.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.playmakers.lifemetrics.data.DateTimePreferences
import com.playmakers.lifemetrics.ui.screens.home.HomeViewModel
import com.playmakers.lifemetrics.ui.theme.LifeMetricsTheme
import kotlinx.coroutines.launch

@Composable
fun ProgressBar(homeViewModel: HomeViewModel = viewModel()){

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dateTimeObj = DateTimePreferences(context)
    val dateTime = dateTimeObj.dateTimeFlow.collectAsState(initial = null)

    Box(
        Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        Surface(
            Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                progress = 1f,
                strokeWidth = 10.dp,
                color = MaterialTheme.colorScheme.inversePrimary
            )
            CircularProgressIndicator(
                progress = homeViewModel.progress,
                strokeWidth = 10.dp,
                color = MaterialTheme.colorScheme.primary
            )
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${homeViewModel.days} Days",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(bottom = 15.dp)
                )
                Text(
                    text = "${homeViewModel.hours}:${homeViewModel.minutes}:${homeViewModel.seconds}"
                )

                if(!dateTime.value.isNullOrBlank()){
                    Text(text = dateTime.value!!)
                }
            }
        }
    }
}

@Composable
fun ActionButtons(homeViewModel: HomeViewModel = viewModel()){

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dateTimeObj = DateTimePreferences(context)

    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        OutlinedButton(
            onClick = { /* Do something! */
                      scope.launch {
                          dateTimeObj.saveDateTime("Success")
                      }
                      },
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
        ) {
            Icon(
                Icons.Filled.Delete,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Clear Data")
        }

        Button(
            onClick = { /* Do something! */
                      homeViewModel.startTime()
                      },
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
        ) {
            Icon(
                Icons.Filled.Refresh,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Gave Up")
        }
    }
}

@Composable
fun Quote(){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text = "Believe you can, and you are halfway there!",
            textAlign = TextAlign.Center
        )
    }
}

@Preview(name = "Progress Bar Dark",uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProgressBarPreview(homeViewModel: HomeViewModel = viewModel()){
    LifeMetricsTheme {
        ProgressBar(homeViewModel)
    }
}

@Preview(name = "Action Buttons Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ActionButtonsPreview(homeViewModel: HomeViewModel = viewModel()){
    LifeMetricsTheme {
        ActionButtons()
    }
}

@Preview(name = "Action Buttons Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun QuotePreview(){
    LifeMetricsTheme {
        Quote()
    }
}



