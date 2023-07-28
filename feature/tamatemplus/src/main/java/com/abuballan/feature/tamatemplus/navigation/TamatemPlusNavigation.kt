package com.abuballan.feature.tamatemplus.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.abuballan.feature.tamatemplus.TamatemPlusRoute

const val tamatemPlusRoute = "tamatem_plus_route"

fun NavController.navigateToTamatemPlus(navOptions: NavOptions? = null) {
    this.navigate(tamatemPlusRoute, navOptions)
}

fun NavGraphBuilder.tamatemPlusScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = tamatemPlusRoute,
    ) {
        TamatemPlusRoute(
            onBackClick,
        )
    }
}
