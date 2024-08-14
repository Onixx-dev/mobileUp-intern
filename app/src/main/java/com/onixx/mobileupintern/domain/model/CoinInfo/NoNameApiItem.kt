package com.onixx.mobileupintern.domain.model

import com.google.gson.annotations.SerializedName

data class NoNameApiItem (

    @SerializedName("decimal_place"    ) var decimalPlace    : String? = null,
    @SerializedName("contract_address" ) var contractAddress : String? = null
    )