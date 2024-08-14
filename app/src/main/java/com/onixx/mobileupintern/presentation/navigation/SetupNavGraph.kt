package com.onixx.mobileupintern.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.onixx.mobileupintern.presentation.screens.InfoScreen.HostInfoScreen
import com.onixx.mobileupintern.presentation.screens.ListScreen.HostListScreen
import com.onixx.mobileupintern.presentation.viewmodel.CurrencyViewModel

@Composable
fun SetupNavGraph(navHostController: NavHostController, currencyViewModel: CurrencyViewModel,  modifier: Modifier = Modifier) {
    NavHost(
        navController = navHostController,
        startDestination = ScreenItem.InfoPage.route
    )
    {
        composable(route = ScreenItem.ListPage.route) {
            HostListScreen(modifier = modifier)
        }
        composable(route = ScreenItem.InfoPage.route) {
            HostInfoScreen(modifier = modifier)
        }

    }
}