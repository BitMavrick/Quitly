package com.playmaker.quitly.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.playmaker.quitly.data.model.Progress
import com.playmaker.quitly.ui.stateModel.MainUiState
import com.playmaker.quitly.ui.stateModel.MainViewModel
import com.playmaker.quitly.ui.stateModel.StatesUiData
import com.playmaker.quitly.ui.theme.CustomTypography
import com.playmaker.quitly.ui.utils.DetailType

@Composable
fun TimeCounter(
    uiState: MainUiState,
    onGaveUpClick: () -> Unit,
    onClearDataClick: () -> Unit,
    onStartClick: () -> Unit,
    timeState: String
){
    val openGaveUpAlertDialog = rememberSaveable { mutableStateOf(false) }
    val openWipeAlertDialog = rememberSaveable { mutableStateOf(false) }

    Card{
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Outlined.Timer,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null,
                )
                Text(
                    text = "Timer",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .aspectRatio(1f)
            ){
                Surface(
                    Modifier
                        .fillMaxSize()
                ){
                    Column(
                        Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "${uiState.days} DAYS",
                            style = MaterialTheme.typography.displaySmall,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(bottom = 15.dp)
                        )
                        Text(
                            text = "${uiState.hours} : ${uiState.minutes} : ${uiState.seconds}",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    CircularProgressIndicator(
                        progress = 1f,
                        strokeWidth = 10.dp,
                        color = MaterialTheme.colorScheme.inversePrimary
                    )
                    CircularProgressIndicator(
                        progress = uiState.progressValue,
                        strokeWidth = 10.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                if(timeState == "-1"){
                    Button(
                        onClick = { onStartClick() },
                        contentPadding = ButtonDefaults.ButtonWithIconContentPadding
                    ) {
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Start Now")
                    }
                }else{
                    OutlinedButton(
                        onClick = { openWipeAlertDialog.value = true },
                        contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                    ) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "Clear data icon",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Clear Data")
                    }

                    Button(
                        onClick = { openGaveUpAlertDialog.value = true },
                        contentPadding = ButtonDefaults.ButtonWithIconContentPadding
                    ) {
                        Icon(
                            Icons.Filled.Refresh,
                            contentDescription = "Gave up icon",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Gave Up")
                    }

                    if(openGaveUpAlertDialog.value){
                        GaveUpAlert(
                            onDismissRequest = { openGaveUpAlertDialog.value = false },
                            onConfirmation = {
                                onGaveUpClick()
                                openGaveUpAlertDialog.value = false
                            }
                        )
                    }

                    if(openWipeAlertDialog.value){
                        WipeAlert(
                            onDismissRequest = { openWipeAlertDialog.value = false },
                            onConfirmation = {
                                onClearDataClick()
                                openWipeAlertDialog.value = false
                            }
                        )
                    }
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(38.dp)
            ) {
                Text(
                    text = uiState.quote,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun GaveUpAlert(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        icon = {
            Icon(
                Icons.Filled.Refresh,
                contentDescription = "Give up icon",
            )
        },
        title = {
            Text(text = "Give up now?")
        },
        text = {
            Text(
                text = "Think twice before what you are doing! You are going to destroying the progress you made so far. That is not what you wanted when you started.",
            )
        },
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Give up")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}

@Composable
fun WipeAlert(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        icon = {
            Icon(
                Icons.Filled.Delete,
                contentDescription = "Give up icon",
            )
        },
        title = {
            Text(text = "Erase all data?")
        },
        text = {
            Text(
                text = "Think twice before what you are doing! You are going to wipe out all the records. Are you sure about this?",
            )
        },
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Yes, I'm sure")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressHistory(
    onDetailPress: ((DetailType) -> Unit)
){
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        ),
        onClick = { onDetailPress(DetailType.HOME_DETAIL) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(60.dp)
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.inversePrimary)

            ) {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Filled.ShowChart,
                        contentDescription = null,
                    )
                }
            }

            Column(
                Modifier
                    .height(60.dp)
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Progress History",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Tap to see your progress details",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun OverviewCard(
    uiState: MainUiState,
    viewModel: MainViewModel,
    statesUiData: StatesUiData,
){
    if(uiState.runningTimeSeconds == 0L){
        Card(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ){
            Text(
                text = "Progress ranks is not available since you don't start yet.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp),
            )
        }
    }else{
        val currentBest = viewModel.statesProgressCard().title
        val days = viewModel.statesProgressCard().days
        val giveUps = statesUiData.timesList.size
        val runningTime = uiState.runningTimeSeconds

        Card(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ){
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                if(giveUps == 0){
                    Text(
                        text = currentBest,
                        style = CustomTypography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Reached $days Days",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                }else{
                    var maxTime = 0L
                    statesUiData.timesList.forEach{ states ->
                        if(states.period > maxTime){
                            maxTime = states.period
                        }
                    }

                    if(runningTime > maxTime){
                        maxTime = runningTime
                    }

                    val allTimeBest = viewModel.maxStatesProgress(maxTime)

                    Text(
                        text = allTimeBest.title,
                        style = CustomTypography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Reached ${allTimeBest.days} Days",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                Divider(
                    thickness = 3.dp,
                    color = MaterialTheme.colorScheme.inversePrimary,
                    modifier = Modifier.padding(vertical = 6.dp)
                )

                Row{
                    Text(
                        text = "Current Best: ",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 3.dp)
                    )

                    Text(
                        text = "$currentBest ($days days)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }

                Row{
                    Text(
                        text = "Total Give ups: ",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        text = "$giveUps Times",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
            }
        }
    }
}

@Composable
fun ProgressCard(
    runningTime: Long,
    progress: Progress
){
    val remainingTime = progress.endTime - runningTime

    val secondsInADay = 24 * 60 * 60
    val days = (remainingTime / secondsInADay).toString()
    val seconds = (remainingTime % 60).toString().padStart(2, '0')
    val minutes = ((remainingTime % 3600) / 60).toString().padStart(2, '0')
    val hours = ((remainingTime % 86400) / 3600).toString().padStart(2, '0')

    Card(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Progress",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
            )
            Row(
                Modifier.padding(vertical = 4.dp),
            ){
                Text(
                    text = buildString {
                        append("Remaining: ")
                        if (days != "0") {
                            append("${days}d ")
                        }
                        append("$hours : $minutes : $seconds")
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
            LinearProgressIndicator(
                progress = ((runningTime.toFloat() - progress.startTime.toFloat())/(progress.endTime.toFloat() - progress.startTime.toFloat())),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp),
                trackColor = MaterialTheme.colorScheme.inversePrimary,
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = progress.title,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = progress.upcoming,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreBoard(
    onDetailPress: ((DetailType) -> Unit)
){
    OutlinedCard(
        modifier = Modifier.padding(top = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        ),
        onClick = { onDetailPress(DetailType.RANK_DETAIL) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(60.dp)
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.inversePrimary)

            ) {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Filled.List,
                        contentDescription = null,
                    )
                }
            }

            Column(
                Modifier
                    .height(60.dp)
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Score Board",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Tap to see the score board",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}