package com.playmaker.quitly.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.playmaker.quitly.data.model.ScreenType
import com.playmaker.quitly.ui.stateModel.MainUiState
import com.playmaker.quitly.ui.theme.CustomTypography
import com.playmaker.quitly.ui.theme.QuitlyTheme
import com.playmaker.quitly.ui.utils.DetailType
import com.playmaker.quitly.ui.utils.NavigationType

@Composable
fun RootDetailComponents(
    uiState: MainUiState,
    navigationType: NavigationType,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed()
    }

    var paddingValue = PaddingValues(0.dp)

    LazyColumn(
        modifier = modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
    ) {
        if(navigationType == NavigationType.BOTTOM_NAVIGATION){

            paddingValue = PaddingValues(horizontal = 16.dp)

            item {
                AppDetailTopBar(
                    uiState,
                    onBackPressed
                )
            }
        }

        if(uiState.currentScreenType == ScreenType.HOME){
            item {
                Column(
                    Modifier.padding(paddingValue)
                ) {
                    ProgressGraph()
                }
            }
        }else{
            item{
                Column(
                    Modifier.padding(paddingValue)
                ) {
                    AchievementCard()
                }
            }
        }
    }
}

@Composable
private fun AppDetailTopBar(
    uiState: MainUiState,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp),
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton(
                onClick = { onBackPressed() }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "More option",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            if(uiState.currentDetailType == DetailType.HOME_DETAIL){
                Text(
                    text = "PROGRESS HISTORY",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
            }else{
                Text(
                    text = "SCORE BOARD",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }

        IconButton(
            onClick = { }
        ) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "More option",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun  ProgressGraph(){
    Card(
        Modifier
            .fillMaxWidth()
    ){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = "Overview",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Card {
                // Dummy Data for the chart
                val steps = 5
                val pointsData = listOf(
                    Point(0f, 0f),
                    Point(1f, 10f),
                    Point(2f, 30f),
                    Point(3f, 15f),
                    Point(4f, 35f),
                )

                val xAxisData = AxisData.Builder()
                    .axisStepSize(70.dp)
                    .backgroundColor(Color.Transparent)
                    .steps(pointsData.size - 1)
                    .labelData { i -> i.toString() }
                    .labelAndAxisLinePadding(15.dp)
                    .axisLineColor(MaterialTheme.colorScheme.tertiary)
                    .axisLabelColor(MaterialTheme.colorScheme.tertiary)
                    .build()

                val yAxisData = AxisData.Builder()
                    .steps(steps)
                    .backgroundColor(Color.Transparent)
                    .labelAndAxisLinePadding(20.dp)
                    .axisLineColor(MaterialTheme.colorScheme.tertiary)
                    .axisLabelColor(MaterialTheme.colorScheme.tertiary)
                    .build()

                val lineCharData = LineChartData(
                    linePlotData = LinePlotData(
                        lines = listOf(
                            Line(
                                dataPoints = pointsData,
                                LineStyle(
                                    color = MaterialTheme.colorScheme.tertiary,
                                    lineType = LineType.SmoothCurve(isDotted = false)
                                ),
                                IntersectionPoint(
                                    color = MaterialTheme.colorScheme.tertiary
                                ),
                                SelectionHighlightPoint(color = MaterialTheme.colorScheme.primary),
                                ShadowUnderLine(
                                    alpha = 0.5f,
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            MaterialTheme.colorScheme.inversePrimary,
                                            Color.Transparent
                                        )
                                    )
                                ),
                                SelectionHighlightPopUp()
                            )
                        ),
                    ),
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    xAxisData = xAxisData,
                    yAxisData = yAxisData,
                    gridLines = GridLines(color = MaterialTheme.colorScheme.outlineVariant)
                )

                LineChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    lineChartData = lineCharData
                )
            }
            
            Text(
                text = "Progress Report",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Avg. duration: 1d 10H",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            ProgressBar()
            Spacer(modifier = Modifier.height(8.dp))
            ProgressBar()
            Spacer(modifier = Modifier.height(8.dp))
            ProgressBar()
            Spacer(modifier = Modifier.height(8.dp))
            ProgressBar()
        }
    }
}

@Composable
fun ProgressBar(){
    Row(
        Modifier
            .fillMaxWidth()
            .height(40.dp)
    ){
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
                .aspectRatio(1f)
                .background(MaterialTheme.colorScheme.inversePrimary)

        ) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "1",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Column(
            Modifier
                .fillMaxSize()
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Duration: 1d 10H 35M",
                style = MaterialTheme.typography.bodySmall
            )

            LinearProgressIndicator(
                progress = 0.4f,
                trackColor = MaterialTheme.colorScheme.inversePrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
            )
        }
    }
}

@Preview
@Composable
fun  ProgressBarPreview(){
    QuitlyTheme {
        ProgressBar()
    }
}

@Preview
@Composable
fun  ProgressGraphPreview(){
    QuitlyTheme {
        ProgressGraph()
    }
}

@Composable
fun AchievementCard(){
    Card(
        colors = CardDefaults.cardColors(
            //containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        ),
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
                Text(
                    text = "Scout",
                    style = CustomTypography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                )
            }
            Divider(
                thickness = 3.dp
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Reach 1 day",
                    style = CustomTypography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}