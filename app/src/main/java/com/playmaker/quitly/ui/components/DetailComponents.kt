package com.playmaker.quitly.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.playmaker.quitly.ui.stateModel.MainUiState

@Composable
fun RootDetailComponents(
    uiState: MainUiState,
    onBackPressed: () -> Unit,
) {
    BackHandler {
        onBackPressed()
    }

    Column {
        Text(text = "This is the details page")
    }
}