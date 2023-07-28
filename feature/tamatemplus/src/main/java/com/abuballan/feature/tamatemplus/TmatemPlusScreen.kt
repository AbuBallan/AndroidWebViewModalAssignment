@file:OptIn(ExperimentalMaterial3Api::class)

package com.abuballan.feature.tamatemplus

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.abuballan.core.webviewer.WebViewer
import com.abuballan.core.webviewer.WebViewerLoadingState
import com.abuballan.core.webviewer.rememberWebViewerState
import com.abuballan.feature.tmatemplus.BuildConfig
import com.abuballan.feature.tmatemplus.R

@Composable
internal fun TamatemPlusRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    TamatemPlusScreen(
        onBackClick = onBackClick,
        modifier = modifier,
    )
}

@Composable
internal fun TamatemPlusScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val webViewerState = rememberWebViewerState(initialUrl = BuildConfig.TAMAMTEM_PLUS_URL)
    Column(
        modifier = modifier
    ) {

        TopAppBar(
            title = {
                Text(
                    text = webViewerState.pageTitle ?: stringResource(id = R.string.tamatem_store)
                )
            },
            navigationIcon = {
                if (webViewerState.canGoBack) {
                    IconButton(onClick = { webViewerState.navigateBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            },
            actions = {
                IconButton(onClick = { webViewerState.refresh() }) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh"
                    )
                }
                if (webViewerState.canGoForward) {
                    IconButton(onClick = { webViewerState.navigateForward() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Forward"
                        )
                    }
                }
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close"
                    )
                }
            }

        )

        val loadingState = webViewerState.loadingState
        if (loadingState is WebViewerLoadingState.Loading) {
            LinearProgressIndicator(
                progress = loadingState.progress,
                modifier = Modifier.fillMaxWidth()
            )
        }

        WebViewer(
            state = webViewerState,
            shouldOverrideUrlLoading = {
                // TODO: HANDLE CUSTOM WEB URLS such as phone numbers, emails ...
                false
            }
        )

        BackHandler(true) {
            if (webViewerState.canGoBack) {
                webViewerState.navigateBack()
            } else {
                onBackClick()
            }
        }
    }

}