package com.abuballan.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.abuballan.feature.home.HomeRoute

const val homeRoute = "home_route"

fun NavGraphBuilder.homeScreen(
    onTamatemPlusClick: () -> Unit,
) {
    composable(
        route = homeRoute,
    ) {
        HomeRoute(
            onTamatemPlusClick,
        )
    }
}
