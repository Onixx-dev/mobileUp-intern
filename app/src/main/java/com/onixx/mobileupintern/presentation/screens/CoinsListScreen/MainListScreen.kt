package com.onixx.mobileupintern.presentation.screens.CoinsListScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.onixx.mobileupintern.domain.model.CoinList.Coin
import com.onixx.mobileupintern.presentation.viewmodel.CurrencyViewModel

/**
 * основной экран, отображающий список валют и котировок
 * @param onListItemClick - лямбда, вызываемая элементом списка при нажатии по нему
 * @see CoinListItem
 * @see CurrencyViewModel.coinListScreenStates
 */
@Composable
fun MainListScreen(
    modifier: Modifier,
    coins: ArrayList<Coin>,
    viewModel: CurrencyViewModel,
    onListItemClick: (String) -> Unit
) {
    Box(modifier = modifier) {
        val isLoading by viewModel.isLoading.collectAsState()
        val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.refreshCoinList() })
        {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                items(coins.toList()) { coin ->
                    CoinListItem(
                        coin = coin,
                        onItemClick = onListItemClick)
                }
            }
        }



    }
}