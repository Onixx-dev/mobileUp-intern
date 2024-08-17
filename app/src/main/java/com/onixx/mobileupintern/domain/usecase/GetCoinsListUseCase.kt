package com.onixx.mobileupintern.domain.usecase

import com.onixx.mobileupintern.domain.model.CoinList.Coin
import com.onixx.mobileupintern.domain.repository.CoinListRepository

/**
 * UseCase-обертка над вызовом репозитория, реализующего CoinListRepository,
 * собирается через DI Koin, см. App
 * @see CoinListRepository
 * @see App
 */
class GetCoinsListUseCase(private val coinListRepository: CoinListRepository) {

    suspend fun execute(currencyKey: String, count: Int) : ArrayList<Coin> {
        val result = coinListRepository.getList(currencyKey, count)
        return result
    }

}