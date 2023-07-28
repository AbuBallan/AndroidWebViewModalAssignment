package com.abuballan.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource


@Composable
internal fun HomeRoute(
    onTamatemPlusClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HomeScreen(
        onTamatemPlusClick = onTamatemPlusClick,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    onTamatemPlusClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.home)) },
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = { onTamatemPlusClick() }) {
                Text(text = stringResource(id = R.string.navigate_to_tamatem_plus_action))
            }
        }
    }
}