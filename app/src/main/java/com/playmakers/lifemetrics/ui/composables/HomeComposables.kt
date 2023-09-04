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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playmakers.lifemetrics.ui.theme.LifeMetricsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    TopAppBar(
        title = {
            Text(
                "LifeMetrics",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Localized description"
                )
            }
        },
    )
}

@Composable
fun ProgressBar(){
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
                color = MaterialTheme.colorScheme.onPrimary
            )
            CircularProgressIndicator(
                progress = 0.4f,
                strokeWidth = 10.dp,
                color = MaterialTheme.colorScheme.primary
            )
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "3 Days",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(bottom = 15.dp)
                )
                Text(
                    text = "06:57:07"
                )
            }
        }
    }
}

@Composable
fun ActionButtons(){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        OutlinedButton(
            onClick = { /* Do something! */ },
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
            onClick = { /* Do something! */ },
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
fun NavigationBar(){
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = null)},
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Info, contentDescription = null)},
            selected = false,
            onClick = {}
        )
    }
}


@Composable
fun Quote(){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text = "Believe you can, and you are halfway there!"
        )
    }
}

@Preview(name = "Top Bar Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TopBarPreview(){
    LifeMetricsTheme {
        TopBar()
    }
}

@Preview(name = "Progress Bar Dark",uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProgressBarPreview(){
    LifeMetricsTheme {
        ProgressBar()
    }
}

@Preview(name = "Action Buttons Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ActionButtonsPreview(){
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

@Preview(name = "Action Buttons Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NavigationBarPreview(){
    LifeMetricsTheme {
        NavigationBar()
    }
}

