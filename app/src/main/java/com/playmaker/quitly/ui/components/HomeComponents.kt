package com.playmaker.quitly.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.playmaker.quitly.R
import com.playmaker.quitly.R.dimen.topbar_padding_vertical

@Composable
fun HomeOnlyContent(
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier,
    ){
        item {
            AppHomeTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(topbar_padding_vertical))
            )
        }
    }
}


@Composable
private fun AppHomeTopBar(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ){
        Text(
            text = "Quitly",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = 16.dp)
        )

        IconButton(onClick = { /* doSomething() */ }) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "More option",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}