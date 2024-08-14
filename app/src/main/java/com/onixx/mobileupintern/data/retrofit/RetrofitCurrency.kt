package com.onixx.mobileupintern.data.retrofit

import com.onixx.mobileupintern.domain.model.CoinInfo
import com.onixx.mobileupintern.domain.model.CoinList.Coin
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitCurrency {

    @GET("coins/markets")
    fun getCurrencyList(
        @Query("apikey") key: String,
        @Query("vs_currency") base: String,
        @Query("per_page") count: Int
    ): Deferred<Response<ArrayList<Coin>>>

    @GET("coins/{id}")
    fun getInfoWithoutAdditions(
        @Path("id") id: String,
        @Query("apikey") key: String,
        @Query("tickets") tickets: Boolean,
        @Query("market_data") marketData: Boolean,
        @Query("community_data") communityData: Boolean,
        @Query("developer_data") developerData: Boolean
    ): Deferred<Response<CoinInfo>>

}