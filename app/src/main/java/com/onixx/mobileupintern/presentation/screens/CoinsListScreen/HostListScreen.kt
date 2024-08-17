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
import com.onixx.mobileupintern.presentation.screens.states.CoinListScreenStates
import com.onixx.mobileupintern.presentation.viewmodel.CurrencyViewModel

/**
 * Хост-экран для экрана списка валют. Является экраном по умолчанию в конроллере навигации.
 * Проверяет стейт экрана во ViewModel и в зависимости от него выдает один из экранов с информацией.
 *
 * @see navHostController
 * @see SetupNavGraph
 * @see CurrencyViewModel
 */

@Composable
fun HostListScreen(
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

                /**
                 * @param refreshAction - принимает лямбду, которая будет вызываться нажатием на кнопку перезагрузки при ошибке
                 */
                is CoinListScreenStates.Error -> ErrorScreen(
                    modifier = modifier,
                    refreshAction = { currencyViewModel.getCoinList() })


                /**
                 * @param coinInfo - коллекция котировок валют, полученная с АПИ и содержащаяся в стейт-холдере
                 * @param onListItemClick - лямбда, которая будет вызвана при клине на элемент списка валют
                 * @see CoinListItem
                 */
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


