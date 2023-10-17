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
import androidx.compose.material.icons.outlined.AutoGraph
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
import com.playmaker.quitly.data.local.ProgressDataSource.progressList
import com.playmaker.quitly.data.model.Progress
import com.playmaker.quitly.data.model.ScreenType
import com.playmaker.quitly.ui.stateModel.MainUiState
import com.playmaker.quitly.ui.stateModel.StatesUiData
import com.playmaker.quitly.ui.theme.CustomTypography
import com.playmaker.quitly.ui.utils.DetailType
import com.playmaker.quitly.ui.utils.NavigationType

@Composable
fun RootDetailComponents(
    uiState: MainUiState,
    statesUiData: StatesUiData,
    navigationType: NavigationType,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val progressList = progressList

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
                    ProgressGraph(
                        statesUiData = statesUiData,
                        runningTime = uiState.runningTimeSeconds,
                    )
                }
            }
        }else{
            item{
                Column(
                    Modifier.padding(paddingValue)
                ) {
                    for(progress in progressList){
                        if(uiState.days.toInt() >= progress.days && uiState.runningTimeSeconds != 0L){
                            AchievementCard(
                                color = MaterialTheme.colorScheme.tertiaryContainer,
                                progress = progress,
                            )
                        }else{
                            AchievementCard(
                                progress = progress
                            )
                        }
                    }
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
fun  ProgressGraph(
    statesUiData: StatesUiData,
    runningTime: Long,
){
    Card(
        Modifier
            .fillMaxWidth()
    ){
        val totalTry = statesUiData.timesList.size

        if(totalTry != 0){
            var maxPoint = 0f
            var progressIndex = 0
            var totalTime = 0f

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    Modifier.padding(bottom = 16.dp)
                ){
                    Icon(
                        Icons.Outlined.AutoGraph,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null,
                    )
                    Text(
                        text = "Overview",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Card {
                    // Dummy Data for the chart
                    val steps = (statesUiData.timesList.size + 1)

                    val pointsData = listOf(Point(0f, 0f)) + statesUiData.timesList.mapIndexed { index, element ->
                        val serial = index.toFloat() + 1f
                        val point = element.period.toFloat()

                        if(point > maxPoint){
                            maxPoint = point
                        }

                        totalTime += point

                        Point(serial, point)
                    } + Point(steps.toFloat(), runningTime.toFloat())

                    if(runningTime.toFloat() > maxPoint){
                        maxPoint = runningTime.toFloat()
                    }

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

                totalTime += runningTime

                val avgTimeInSeconds = (totalTime / (totalTry + 1))

                val days = getDays(avgTimeInSeconds)
                val hours = getHours(avgTimeInSeconds)
                val minutes = getMinutes(avgTimeInSeconds)
                val seconds = getSeconds(avgTimeInSeconds)

                val text = buildString {
                    if (days > 0) append("${days}d ")
                    if (hours > 0) append("${hours}h ")
                    if (minutes > 0) append("${minutes}m ")
                    if (seconds > 0 || (days == 0 && hours == 0 && minutes == 0)) append("${seconds}s")
                }

                Text(
                    text = "Avg. duration: $text",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )

                statesUiData.timesList.mapIndexed { index, element ->
                    progressIndex = index + 1
                    val point = element.period.toFloat()

                    Spacer(modifier = Modifier.height(12.dp))
                    ProgressBar(
                        serial = progressIndex,
                        duration = point,
                        maxValue = maxPoint
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                ProgressBar(
                    serial = progressIndex + 1,
                    duration = runningTime.toFloat(),
                    maxValue = maxPoint
                )
            }
        }else{
            Text(
                text = "Progress history is not available since you don't have any history yet.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}

@Composable
fun ProgressBar(
    serial: Int,
    duration: Float,
    maxValue: Float,
){
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
                    text = serial.toString(),
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
            val days = getDays(duration)
            val hours = getHours(duration)
            val minutes = getMinutes(duration)
            val seconds = getSeconds(duration)

            val text = buildString {
                if (days > 0) append("${days}d ")
                if (hours > 0) append("${hours}h ")
                if (minutes > 0) append("${minutes}m ")
                if (seconds > 0 || (days == 0 && hours == 0 && minutes == 0)) append("${seconds}s")
            }

            Text(
                text = "Duration: $text",
                style = MaterialTheme.typography.bodySmall
            )

            LinearProgressIndicator(
                progress = duration / maxValue,
                trackColor = MaterialTheme.colorScheme.inversePrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
            )
        }
    }
}

@Composable
fun AchievementCard(
    color : Color = MaterialTheme.colorScheme.secondaryContainer,
    progress : Progress
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = color,
        ),
        modifier = Modifier.padding(bottom = 16.dp)
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
                    text = progress.title,
                    style = CustomTypography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                )
            }
            Divider(
                thickness = 3.dp,
                color = MaterialTheme.colorScheme.inversePrimary,
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Reach ${progress.days} day",
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

private fun getDays(seconds: Float) : Int {
    val secondsInADay = 24 * 60 * 60
    return (seconds / secondsInADay).toInt()
}

private fun getHours(seconds: Float) : Int {
    return ((seconds % 86400) / 3600).toInt()
}

private fun getMinutes(seconds: Float) : Int {
    return ((seconds % 3600) / 60).toInt()
}

private fun getSeconds(seconds: Float) : Int {
    return (seconds % 60).toInt()
}