package com.playmakers.lifemetrics.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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
fun NavigationBar(
    onHomeButtonClicked: () ->Unit,
    onStateButtonClicked: () ->Unit,
    selectedValue: Boolean
){
    androidx.compose.material3.NavigationBar(

        modifier = Modifier.height(50.dp)
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = null) },
            selected = selectedValue,
            onClick = { onHomeButtonClicked() }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.BarChart, contentDescription = null) },
            selected = !selectedValue,
            onClick = { onStateButtonClicked() }
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

@Preview(name = "Action Buttons Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NavigationBarPreview(){
    LifeMetricsTheme {
        NavigationBar(
            onHomeButtonClicked = {},
            onStateButtonClicked = {},
            selectedValue  = true
        )
    }
}