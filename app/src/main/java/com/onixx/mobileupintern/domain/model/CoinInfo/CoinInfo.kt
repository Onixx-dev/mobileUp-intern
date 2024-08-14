package com.onixx.mobileupintern.domain.model

import com.google.gson.annotations.SerializedName


data class CoinInfo (

  @SerializedName("id"                              ) var id                           : String?           = null,
  @SerializedName("symbol"                          ) var symbol                       : String?           = null,
  @SerializedName("name"                            ) var name                         : String?           = null,
  @SerializedName("web_slug"                        ) var webSlug                      : String?           = null,
  @SerializedName("asset_platform_id"               ) var assetPlatformId              : String?           = null,
  @SerializedName("platforms"                       ) var platforms                    : Platforms?        = Platforms(),
  @SerializedName("detail_platforms"                ) var detailPlatforms              : DetailPlatforms?  = DetailPlatforms(),
  @SerializedName("block_time_in_minutes"           ) var blockTimeInMinutes           : Int?              = null,
  @SerializedName("hashing_algorithm"               ) var hashingAlgorithm             : String?           = null,
  @SerializedName("categories"                      ) var categories                   : ArrayList<String> = arrayListOf(),
  @SerializedName("preview_listing"                 ) var previewListing               : Boolean?          = null,
  @SerializedName("public_notice"                   ) var publicNotice                 : String?           = null,
  @SerializedName("additional_notices"              ) var additionalNotices            : ArrayList<String> = arrayListOf(),
  @SerializedName("localization"                    ) var localization                 : Localization?     = Localization(),
  @SerializedName("description"                     ) var description                  : Description?      = Description(),
  @SerializedName("links"                           ) var links                        : Links?            = Links(),
  @SerializedName("image"                           ) var image                        : Image?            = Image(),
  @SerializedName("country_origin"                  ) var countryOrigin                : String?           = null,
  @SerializedName("genesis_date"                    ) var genesisDate                  : String?           = null,
  @SerializedName("sentiment_votes_up_percentage"   ) var sentimentVotesUpPercentage   : Double?           = null,
  @SerializedName("sentiment_votes_down_percentage" ) var sentimentVotesDownPercentage : Double?           = null,
  @SerializedName("watchlist_portfolio_users"       ) var watchlistPortfolioUsers      : Int?              = null,
  @SerializedName("market_cap_rank"                 ) var marketCapRank                : Int?              = null,
  @SerializedName("status_updates"                  ) var statusUpdates                : ArrayList<String> = arrayListOf(),
  @SerializedName("last_updated"                    ) var lastUpdated                  : String?           = null

)