package com.onixx.mobileupintern.presentation.screens.CoinInfoScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.onixx.mobileupintern.presentation.screens.shared.ErrorScreen
import com.onixx.mobileupintern.presentation.screens.shared.InfoTopBar
import com.onixx.mobileupintern.presentation.screens.shared.LoadingScreen
import com.onixx.mobileupintern.presentation.screens.states.CoinInfoScreenStates
import com.onixx.mobileupintern.presentation.viewmodel.CurrencyViewModel


/**
 * Хост-экран для экрана информации о конкретной валюте.
 * Проверяет стейт экрана во ViewModel и в зависимости от него выдает один из экранов с информацией.
 *
 * @see navHostController
 * @see SetupNavGraph
 * @see CurrencyViewModel
 */
@Composable
fun HostInfoScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    currencyViewModel: CurrencyViewModel
) {
    /**
     * Scaffold определен здесь, т.к для разных хост-экранов необходим разный TopBar
     */
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            InfoTopBar(
                modifier = modifier,
                title = currencyViewModel.coinName,
                navHostController = navHostController
            )
        })
    { padding ->
        Box(
            modifier = Modifier.padding(padding)
        ) {
            when (currencyViewModel.coinInfoScreenStates) {

                is CoinInfoScreenStates.Loading -> LoadingScreen(
                    modifier = modifier
                )

                /**
                 * @param refreshAction - принимает лямбду, которая будет вызываться нажатием на кнопку перезагрузки при ошибке
                 */
                is CoinInfoScreenStates.Error -> ErrorScreen(
                    modifier = modifier,
                    refreshAction = { currencyViewModel.getCoinInfo() })

                /**
                 * @param coinInfo - информация о конукретной валюте, полученная с АПИ и содержащаяся в стейт-холдере
                 */
                is CoinInfoScreenStates.Success -> MainInfoScreen(
                    modifier = modifier,
                    coinInfo = (currencyViewModel.coinInfoScreenStates as CoinInfoScreenStates.Success).coinInfo
                )
            }

        }
    }
}