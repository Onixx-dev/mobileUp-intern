package com.onixx.mobileupintern.domain.usecase

import com.onixx.mobileupintern.domain.model.CoinList.Coin
import com.onixx.mobileupintern.domain.repository.CoinListRepository

class UploadCoinsUseCase(private val coinListRepository: CoinListRepository) {

    suspend fun execute(currencyKey: String, count: Int) : ArrayList<Coin> {
        val result = coinListRepository.getList(currencyKey, count)
        return result
    }

}