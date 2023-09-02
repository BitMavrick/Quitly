package com.playmakers.lifemetrics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playmakers.lifemetrics.ui.theme.LifeMetricsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LifeMetricsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Greeting("Android")
                    ProgressBar()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProgressBar(){
    Surface(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        CircularProgressIndicator(
            progress = 1f,
            strokeWidth = 10.dp,
            color = Color.Gray
        )

        CircularProgressIndicator(
            progress = 0.4f,
            strokeWidth = 10.dp,
        )
    }
}