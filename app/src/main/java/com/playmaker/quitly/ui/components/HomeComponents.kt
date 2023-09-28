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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.ShowChart
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playmaker.quitly.ui.theme.QuitlyTheme
import com.playmaker.quitly.ui.utils.DetailType

@Composable
fun TimeCounter(){
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
                            text = "0 DAYS",
                            style = MaterialTheme.typography.displaySmall,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(bottom = 15.dp)
                        )
                        Text(
                            text = "00 : 00 : 00",
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }

                    CircularProgressIndicator(
                        progress = 1f,
                        strokeWidth = 10.dp,
                        color = MaterialTheme.colorScheme.inversePrimary
                    )
                    CircularProgressIndicator(
                        progress = 0.4f,
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
                OutlinedButton(
                    onClick = { },
                    contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
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
                    onClick = { },
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
    }
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
                    Icons.Outlined.ShowChart,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null,
                )
                Text(
                    text = "Progress History",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row{
                Text(
                    text = "Tap to see the progress >",
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RankCard(
    onDetailPress: ((DetailType) -> Unit)
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        ),
        onClick = { onDetailPress(DetailType.RANK_DETAIL) }
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
                    text = "Rank Card",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(
                Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "- This is the rank card",
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimeCounterPreview(){
    QuitlyTheme {
        TimeCounter()
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressHistoryPreview(){
    QuitlyTheme {
        ProgressHistory(onDetailPress = { })
    }
}