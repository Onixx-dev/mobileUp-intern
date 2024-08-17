package com.onixx.mobileupintern.presentation.screens.CoinsListScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.onixx.mobileupintern.presentation.navigation.ScreenItem
import com.onixx.mobileupintern.presentation.screens.shared.CurrencyTopBar
import com.onixx.mobileupintern.presentation.screens.shared.ErrorScreen
import com.onixx.mobileupintern.presentation.screens.shared.LoadingScreen
import com.onixx.mobileupintern.presentation.viewmodel.CurrencyViewModel
import com.onixx.mobileupintern.presentation.screens.states.CoinListScreenStates

@Composable
fun HostListScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    currencyViewModel: CurrencyViewModel
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CurrencyTopBar(
                modifier = Modifier,
                items = currencyViewModel.currencyArray,
                onChipClick = { currencyViewModel.changeBaseCurrency(it) })
        }
    ) { padding ->
        Box(
            modifier = Modifier.padding(padding)
        ) {

            when (currencyViewModel.coinListScreenStates) {

                is CoinListScreenStates.Loading -> LoadingScreen(
                    modifier = modifier
                )

                is CoinListScreenStates.Error -> ErrorScreen(
                    modifier = modifier,
                    refreshAction = { currencyViewModel.getCoinList() })

                is CoinListScreenStates.Success -> MainListScreen(
                    modifier = modifier,
                    coins = (currencyViewModel.coinListScreenStates as CoinListScreenStates.Success).coins,
                    viewModel = currencyViewModel,
                    onListItemClick = {
                        currencyViewModel.getCoinInfo(it)
                        navHostController.navigate(ScreenItem.InfoPage.route)
                    }
                )
            }

        }
    }


}


