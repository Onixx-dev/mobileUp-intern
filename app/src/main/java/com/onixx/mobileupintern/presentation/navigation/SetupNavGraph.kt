package com.onixx.mobileupintern.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.onixx.mobileupintern.presentation.screens.CoinInfoScreen.HostInfoScreen
import com.onixx.mobileupintern.presentation.screens.CoinsListScreen.HostListScreen
import com.onixx.mobileupintern.presentation.viewmodel.CurrencyViewModel

@Composable
fun SetupNavGraph(navHostController: NavHostController, currencyViewModel: CurrencyViewModel,  modifier: Modifier = Modifier) {
    NavHost(
        navController = navHostController,
        startDestination = ScreenItem.ListPage.route
    )
    {
        composable(
            route = ScreenItem.ListPage.route,
            enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None }
        ) {
            HostListScreen(modifier = modifier, navHostController , currencyViewModel)
        }
        composable(route = ScreenItem.InfoPage.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            HostInfoScreen(modifier = modifier, navHostController , currencyViewModel)
        }

    }
}