package com.onixx.mobileupintern.presentation.viewmodel.states

import com.onixx.mobileupintern.domain.model.CoinList.Coin

sealed class InfoScreenStates {

    data object Loading : InfoScreenStates()

    data class Error( val refreshAction: () -> Unit ) : InfoScreenStates()

    data class Success(var coins: ArrayList<Coin>) : InfoScreenStates()
}