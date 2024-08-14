package com.onixx.mobileupintern.domain.repository

import com.onixx.mobileupintern.domain.model.CoinList.Coin

interface CoinListRepository {

    suspend fun getList(currencyKey: String, count: Int) : ArrayList<Coin>

}