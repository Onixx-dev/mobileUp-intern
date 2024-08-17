package com.onixx.mobileupintern.presentation.screens.states

import com.onixx.mobileupintern.domain.model.CoinList.Coin

sealed class CoinListScreenStates {

    data object Loading : CoinListScreenStates()

    data class Error( val refreshAction: () -> Unit ) : CoinListScreenStates()

    data class Success(var coins: ArrayList<Coin>) : CoinListScreenStates()
}