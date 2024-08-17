package com.onixx.mobileupintern.data.repositoryImpl

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.onixx.mobileupintern.data.retrofit.RetrofitCurrency
import com.onixx.mobileupintern.domain.model.CoinInfo
import com.onixx.mobileupintern.domain.repository.CoinInfoRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * реализация репозитория для получения информации о конкретной валюте
 */
class CoinInfoRepositoryImpl : CoinInfoRepository {

    override suspend fun getInfoWithoutAdditions(currencyName: String): CoinInfo {
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(RetrofitCurrency::class.java)

        /**
         * @param id - ключ крипто-валюты, о которой получаем доп информацию
         * все остальное - флаги для получения дополнительной информации о разных аспектах валюты.
         * В рамках тз не нужны, поэтому по умолчанию false
         */
        val result = retrofit.getInfoWithoutAdditions(
            key = "CG-hm6CxFoi5hCDggEdT9FM9KN4",
            id = currencyName,
            tickets = false, marketData = false, communityData = false, developerData = false
        ).await()

        return result.body() ?: CoinInfo()
    }
}

