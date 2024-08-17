package com.onixx.mobileupintern.presentation.screens.CoinInfoScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.onixx.mobileupintern.presentation.navigation.ScreenItem
import com.onixx.mobileupintern.presentation.screens.CoinsListScreen.MainListScreen
import com.onixx.mobileupintern.presentation.screens.shared.CurrencyTopBar
import com.onixx.mobileupintern.presentation.screens.shared.ErrorScreen
import com.onixx.mobileupintern.presentation.screens.shared.InfoTopBar
import com.onixx.mobileupintern.presentation.screens.shared.LoadingScreen
import com.onixx.mobileupintern.presentation.screens.states.CoinInfoScreenStates
import com.onixx.mobileupintern.presentation.viewmodel.CurrencyViewModel
import com.onixx.mobileupintern.presentation.screens.states.CoinListScreenStates

@Composable
fun HostInfoScreen(modifier: Modifier = Modifier,
                   navHostController: NavHostController,
                   currencyViewModel: CurrencyViewModel
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {InfoTopBar(modifier = modifier,
            title = currencyViewModel.coinName,
            navHostController = navHostController) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)
        ) {
            when (currencyViewModel.coinInfoScreenStates) {

                is CoinInfoScreenStates.Loading -> LoadingScreen(
                    modifier = modifier
                )

                is CoinInfoScreenStates.Error -> ErrorScreen(
                    modifier = modifier,
                    refreshAction = { currencyViewModel.getCoinInfo() })

                is CoinInfoScreenStates.Success -> MainInfoScreen(
                    modifier = modifier,
                    coinInfo = (currencyViewModel.coinInfoScreenStates as CoinInfoScreenStates.Success).coinInfo
                )
            }

        }
    }
}