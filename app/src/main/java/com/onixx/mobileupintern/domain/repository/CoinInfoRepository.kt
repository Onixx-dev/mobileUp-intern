package com.onixx.mobileupintern.domain.repository

import com.onixx.mobileupintern.domain.model.CoinInfo

interface CoinInfoRepository {

    suspend fun getInfoWithoutAdditions(currencyName: String) : CoinInfo

}