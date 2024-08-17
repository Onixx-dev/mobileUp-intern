package com.onixx.mobileupintern.domain.repository

import com.onixx.mobileupintern.domain.model.CoinInfo


/**
 * Интерфейс репозитория для получения расширенной информации о валюте
 * возвращает обьект CoinInfo
 * @see CoinInfo
 * @see ItemSelectedUseCase
 */
interface CoinInfoRepository {

    suspend fun getInfoWithoutAdditions(currencyName: String) : CoinInfo

}