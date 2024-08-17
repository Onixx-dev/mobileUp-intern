package com.onixx.mobileupintern.domain.repository

import com.onixx.mobileupintern.domain.model.CoinList.Coin


/**
 * Интерфейс репозитория для получения списка валют
 * возвращает ArrayList<Coin>
 * @see Coin
 * @see GetCoinsListUseCase
 */
interface CoinListRepository {

    suspend fun getList(currencyKey: String, count: Int) : ArrayList<Coin>

}