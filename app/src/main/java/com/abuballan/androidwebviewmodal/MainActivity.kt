package com.abuballan.androidwebviewmodal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.abuballan.androidwebviewmodal.core.data.util.NetworkMonitor
import com.abuballan.androidwebviewmodal.ui.ModalWebBrowseApp
import com.abuballan.core.designsystem.ModalWebBrowseTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModalWebBrowseTheme {
                ModalWebBrowseApp(
                    networkMonitor = networkMonitor,
                )
            }
        }
    }
}