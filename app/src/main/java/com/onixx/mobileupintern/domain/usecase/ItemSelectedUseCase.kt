package com.onixx.mobileupintern.domain.usecase

import com.onixx.mobileupintern.domain.model.CoinInfo
import com.onixx.mobileupintern.domain.repository.CoinInfoRepository

class ItemSelectedUseCase(private val coinInfoRepository: CoinInfoRepository) {

        suspend fun execute(currencyName: String) : CoinInfo {
            val result = coinInfoRepository.getInfoWithoutAdditions(currencyName)
            return result
        }

}