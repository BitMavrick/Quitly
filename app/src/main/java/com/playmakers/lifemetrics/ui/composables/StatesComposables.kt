package com.playmakers.lifemetrics.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playmakers.lifemetrics.ui.theme.LifeMetricsTheme

@Composable
fun OverviewCard(){
    Card(
        Modifier.fillMaxWidth().padding(vertical = 8.dp)
    ){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Corporal",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Reached 5 Days",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Divider(
                thickness = 3.dp,
                color = MaterialTheme.colorScheme.inversePrimary,
                modifier = Modifier.padding(vertical = 6.dp)
            )

            Row{
                Text(
                    text = "Current Best: ",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 3.dp)
                )

                Text(
                    text = "Scout",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }

            Row{
                Text(
                    text = "Total Give Ups: ",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "3 Times",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }
    }
}

@Preview(name = "Action Buttons Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun OverviewCardPreview(){
    LifeMetricsTheme {
        OverviewCard()
    }
}