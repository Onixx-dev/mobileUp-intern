package com.onixx.mobileupintern.presentation.screens.CoinsListScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.onixx.mobileupintern.presentation.screens.shared.ErrorScreen
import com.onixx.mobileupintern.presentation.screens.shared.LoadingScreen
import com.onixx.mobileupintern.presentation.viewmodel.CurrencyViewModel
import com.onixx.mobileupintern.presentation.viewmodel.states.InfoScreenStates

@Composable
fun HostListScreen(modifier: Modifier = Modifier, currencyViewModel: CurrencyViewModel) {
    when (currencyViewModel.infoScreenStates) {

        is InfoScreenStates.Loading -> LoadingScreen(
            modifier = modifier )

        is InfoScreenStates.Error -> ErrorScreen(
            modifier = modifier,
            refreshAction = {currencyViewModel.getCoinList()} )

        is InfoScreenStates.Success -> MainListScreen(
            modifier = modifier,
            coins = (currencyViewModel.infoScreenStates as InfoScreenStates.Success).coins,
            numberFormat = currencyViewModel.numberFormat,
            viewModel = currencyViewModel)
    }

}


