package com.onixx.mobileupintern.presentation.screens.states

import com.onixx.mobileupintern.domain.model.CoinInfo
import com.onixx.mobileupintern.domain.model.CoinList.Coin


sealed class CoinInfoScreenStates {

    data object Loading : CoinInfoScreenStates()

    data class Error( val refreshAction: () -> Unit ) : CoinInfoScreenStates()

    data class Success(var coinInfo: CoinInfo) : CoinInfoScreenStates()
}