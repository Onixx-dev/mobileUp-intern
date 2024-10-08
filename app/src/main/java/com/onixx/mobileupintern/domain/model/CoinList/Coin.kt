package com.onixx.mobileupintern.domain.model.CoinList

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Coin (

    @SerializedName("id"                               ) var id                           : String? = null,
    @SerializedName("symbol"                           ) var symbol                       : String? = null,
    @SerializedName("name"                             ) var name                         : String? = null,
    @SerializedName("image"                            ) var image                        : String? = null,
    @SerializedName("current_price"                    ) var currentPrice                 : Double?    = null,
    @SerializedName("market_cap"                       ) var marketCap                    : Double?    = null,
    @SerializedName("market_cap_rank"                  ) var marketCapRank                : Double?    = null,
    @SerializedName("fully_diluted_valuation"          ) var fullyDilutedValuation        : Double?    = null,
    @SerializedName("total_volume"                     ) var totalVolume                  : Double?    = null,
    @SerializedName("high_24h"                         ) var high24h                      : Double?    = null,
    @SerializedName("low_24h"                          ) var low24h                       : Double?    = null,
    @SerializedName("price_change_24h"                 ) var priceChange24h               : Double? = null,
    @SerializedName("price_change_percentage_24h"      ) var priceChangePercentage24h     : Double? = null,
    @SerializedName("market_cap_change_24h"            ) var marketCapChange24h           : Double?    = null,
    @SerializedName("market_cap_change_percentage_24h" ) var marketCapChangePercentage24h : Double? = null,
    @SerializedName("circulating_supply"               ) var circulatingSupply            : Double?    = null,
    @SerializedName("total_supply"                     ) var totalSupply                  : Double?    = null,
    @SerializedName("max_supply"                       ) var maxSupply                    : Double?    = null,
    @SerializedName("ath"                              ) var ath                          : Double?    = null,
    @SerializedName("ath_change_percentage"            ) var athChangePercentage          : Double? = null,
    @SerializedName("ath_date"                         ) var athDate                      : String? = null,
    @SerializedName("atl"                              ) var atl                          : Double? = null,
    @SerializedName("atl_change_percentage"            ) var atlChangePercentage          : Double? = null,
    @SerializedName("atl_date"                         ) var atlDate                      : String? = null,
    @SerializedName("roi"                              ) var roi                          : Roi? = null,
    @SerializedName("last_updated"                     ) var lastUpdated                  : String? = null,

    @Expose(serialize = false, deserialize = false)
    var currentPriceStr: String,
    @Expose(serialize = false, deserialize = false)
    var percentChangesStr: String

)