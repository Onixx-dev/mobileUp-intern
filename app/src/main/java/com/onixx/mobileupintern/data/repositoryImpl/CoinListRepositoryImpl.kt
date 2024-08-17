package com.onixx.mobileupintern.data.repositoryImpl

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.onixx.mobileupintern.data.retrofit.RetrofitCurrency
import com.onixx.mobileupintern.domain.model.CoinList.Coin
import com.onixx.mobileupintern.domain.repository.CoinListRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * реализация репозитория для получения списка валют с API.
 */
class CoinListRepositoryImpl : CoinListRepository {

    override suspend fun getList(currencyKey: String, count: Int): ArrayList<Coin> {
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(RetrofitCurrency::class.java)

        /**
         * @param base - базовая валюта, для которой будет строиться курс
         * @param count - кол-во валют в ответе сервера
         */
        val result = retrofit.getCurrencyList(
            key = "CG-hm6CxFoi5hCDggEdT9FM9KN4",
            base = currencyKey,
            count = count
        ).await()

        return result.body()!!
    }
}