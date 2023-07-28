package com.abuballan.core.webviewer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.accompanist.web.LoadingState
import com.google.accompanist.web.WebViewNavigator
import com.google.accompanist.web.WebViewState
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState

@Composable
fun rememberWebViewerState(
    initialUrl: String
): WebViewerState {
    val webViewState: WebViewState = rememberWebViewState(url = initialUrl)
    val webViewNavigator: WebViewNavigator = rememberWebViewNavigator()
    return remember(
        webViewState,
        webViewNavigator,
    ) {
        WebViewerState(
            webViewState = webViewState,
            webViewNavigator = webViewNavigator
        )
    }
}

class WebViewerState(
    val webViewState: WebViewState,
    val webViewNavigator: WebViewNavigator
) {
    val loadingState: WebViewerLoadingState
        get() {
            return when (val state = webViewState.loadingState) {
                LoadingState.Initializing -> WebViewerLoadingState.Initializing
                is LoadingState.Loading -> WebViewerLoadingState.Loading(state.progress)
                LoadingState.Finished -> WebViewerLoadingState.Finished
            }
        }

    val canGoBack: Boolean
        get() = webViewNavigator.canGoBack

    val canGoForward: Boolean
        get() = webViewNavigator.canGoForward

    fun navigateBack() = webViewNavigator.navigateBack()

    fun navigateForward() = webViewNavigator.navigateForward()

    fun refresh() = webViewNavigator.reload()

    val pageTitle
        get() = webViewState.pageTitle

}

sealed class WebViewerLoadingState {
    /**
     * Describes a WebView that has not yet loaded for the first time.
     */
    object Initializing : WebViewerLoadingState()

    /**
     * Describes a webview between `onPageStarted` and `onPageFinished` events, contains a
     * [progress] property which is updated by the webview.
     */
    data class Loading(val progress: Float) : WebViewerLoadingState()

    /**
     * Describes a webview that has finished loading content.
     */
    object Finished : WebViewerLoadingState()
}