package com.onixx.mobileupintern.data.repositoryImpl

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.onixx.mobileupintern.data.retrofit.RetrofitCurrency
import com.onixx.mobileupintern.domain.model.CoinList.Coin
import com.onixx.mobileupintern.domain.repository.CoinListRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoinListRepositoryImpl : CoinListRepository {



    override suspend fun getList(currencyKey: String, count: Int): ArrayList<Coin> {
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(RetrofitCurrency::class.java)

        val result = retrofit.getCurrencyList(
            key = "CG-hm6CxFoi5hCDggEdT9FM9KN4",
            base = currencyKey,
            count = count
        ).await()

        return result.body() ?: ArrayList<Coin>()
    }
}