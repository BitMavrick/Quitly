package com.playmakers.lifemetrics.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import com.playmakers.lifemetrics.ui.theme.LifeMetricsTheme

@Composable
fun OverviewCard(){
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

@Composable
fun GraphCard(){
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
                text = "Struggle Graph",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Card(){
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

@Preview(name = "Action Buttons Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GraphCardPreview(){
    LifeMetricsTheme {
        GraphCard()
    }
}