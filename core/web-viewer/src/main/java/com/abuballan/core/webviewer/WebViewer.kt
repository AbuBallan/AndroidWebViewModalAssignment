package com.abuballan.core.webviewer

import android.annotation.SuppressLint
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView

@Composable
fun WebViewer(
    state: WebViewerState,
    shouldOverrideUrlLoading: (url: String) -> Boolean,
    modifier: Modifier = Modifier
) {

    WebView(
        state = state.webViewState,
        modifier = modifier,
        navigator = state.webViewNavigator,
        onCreated = { webView ->
            // `WebView.settings` manages the settings for a WebView.
            // See: https://developer.android.com/reference/android/webkit/WebSettings
            with(webView.settings) {
                // Note: it can enable cross site scripting (xss) vulnerabilities
                @SuppressLint("SetJavaScriptEnabled")
                javaScriptEnabled = true

                // This enables Javascript `window.localStorage` to exist and be usable by the app.
                domStorageEnabled = true
            }
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        },
        client = setupWebViewClient(shouldOverrideUrlLoading)
    )
}

/**
 * Create a custom [WebViewClient]. It is responsible for most of the
 * actions that occur inside a WebView. For example allows you to
 *  - intercept url requests for special handling
 */
private fun setupWebViewClient(
    shouldOverrideUrlLoading: (url: String) -> Boolean,
) = object : AccompanistWebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val url = request?.url?.toString() ?: return false
        return shouldOverrideUrlLoading(url)
    }
}