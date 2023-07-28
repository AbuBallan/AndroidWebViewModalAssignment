package com.abuballan.androidwebviewmodal.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abuballan.androidwebviewmodal.R
import com.abuballan.androidwebviewmodal.core.data.util.NetworkMonitor
import com.abuballan.androidwebviewmodal.navigation.ModalWebBrowseAppNavHost

@Composable
fun ModalWebBrowseApp(
    networkMonitor: NetworkMonitor,
    appState: ModalWebBrowseAppState = rememberModalWebBrowseAppState(
        networkMonitor = networkMonitor
    ),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()

    // If user is not connected to the internet show a snack bar to inform them.
    val notConnectedMessage = stringResource(R.string.not_connected)
    LaunchedEffect(isOffline) {
        if (isOffline) {
            snackbarHostState.showSnackbar(
                message = notConnectedMessage,
                duration = SnackbarDuration.Indefinite,
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { padding ->
        ModalWebBrowseAppNavHost(
            appState = appState,
            modifier = Modifier.padding(padding)
        )
    }
}