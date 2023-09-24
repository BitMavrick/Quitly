package com.playmaker.quitly.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playmaker.quitly.ui.theme.QuitlyTheme


@Composable
fun TimeCounter(){
    Box(
        Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ){
        Surface(
            Modifier.fillMaxSize()
        ){
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
}

@Preview(showBackground = true)
@Composable
fun TimeCounterPreview(){
    QuitlyTheme {
        TimeCounter()
    }
}