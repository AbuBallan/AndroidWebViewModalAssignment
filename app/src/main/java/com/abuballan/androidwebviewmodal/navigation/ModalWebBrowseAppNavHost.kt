package com.abuballan.androidwebviewmodal.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.abuballan.androidwebviewmodal.ui.ModalWebBrowseAppState
import com.abuballan.feature.home.navigation.homeRoute
import com.abuballan.feature.home.navigation.homeScreen
import com.abuballan.feature.tamatemplus.navigation.navigateToTamatemPlus
import com.abuballan.feature.tamatemplus.navigation.tamatemPlusScreen

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun ModalWebBrowseAppNavHost(
    appState: ModalWebBrowseAppState,
    modifier: Modifier = Modifier,
    startDestination: String = homeRoute,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen(
            onTamatemPlusClick = {
                navController.navigateToTamatemPlus()
            }
        )
        tamatemPlusScreen(
            onBackClick = navController::popBackStack
        )
    }
}