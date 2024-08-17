package com.onixx.mobileupintern.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onixx.mobileupintern.domain.usecase.ItemSelectedUseCase
import com.onixx.mobileupintern.domain.usecase.UploadCoinsUseCase
import com.onixx.mobileupintern.presentation.screens.states.CoinInfoScreenStates
import com.onixx.mobileupintern.presentation.screens.states.CoinListScreenStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

class CurrencyViewModel(
    private val uploadCoinsUseCase: UploadCoinsUseCase,
    private val itemSelectedUseCase: ItemSelectedUseCase
) : ViewModel() {


    var coinListScreenStates: CoinListScreenStates by mutableStateOf(CoinListScreenStates.Loading)
    var coinInfoScreenStates: CoinInfoScreenStates by mutableStateOf(CoinInfoScreenStates.Loading)

    private val _isLoading = MutableStateFlow(false)
    var isLoading = _isLoading.asStateFlow()


    var numberFormat: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
    val currencyArray = arrayListOf("usd", "rub")

    private val uploadCount = 30
    private var currencyName: String by mutableStateOf(currencyArray.first())
    var coinName = "bitcoin"


    fun changeBaseCurrency(newCurrency: String) {
        currencyName = newCurrency
        numberFormat = when (newCurrency) {
            "usd" -> NumberFormat.getCurrencyInstance(Locale.US)
            "rub" -> NumberFormat.getCurrencyInstance(Locale("ru", "RU"))
            else -> NumberFormat.getCurrencyInstance(Locale.US)
        }
        getCoinList()
    }



    fun getCoinList() {
        coinListScreenStates = CoinListScreenStates.Loading
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                coinListScreenStates =
                    try {
                        val list = uploadCoinsUseCase.execute(currencyName, uploadCount)
                        var prefix: String

                        for (coin in list) {
                            coin.currentPriceStr = numberFormat.format(coin.currentPrice)

                            if (coin.priceChangePercentage24h!! < 0)
                                prefix = ""
                            else
                                prefix = "+"

                            coin.percentChangesStr = buildString {
                                append(prefix)
                                append(DecimalFormat("#.##").format(coin.priceChangePercentage24h))
                                append("%")
                            }
                        }
                        CoinListScreenStates.Success(list)
                    } catch (e: Exception) {
                        CoinListScreenStates.Error(refreshAction = { getCoinList() })
                    }
            }
        }
    }

    fun refreshCoinList() {
        _isLoading.value = true

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                coinListScreenStates =
                    try {
                        val list = uploadCoinsUseCase.execute(currencyName, uploadCount)
                        var prefix: String

                        for (coin in list) {
                            coin.currentPriceStr = numberFormat.format(coin.currentPrice)

                            if (coin.priceChangePercentage24h!! < 0)
                                prefix = ""
                            else
                                prefix = "+"

                            coin.percentChangesStr = buildString {
                                append(prefix)
                                append(DecimalFormat("#.##").format(coin.priceChangePercentage24h))
                                append("%")
                            }
                        }
                        CoinListScreenStates.Success(list)
                    } catch (e: Exception) {
                        CoinListScreenStates.Error(refreshAction = { getCoinList() })
                    } finally {
                        _isLoading.value = false
                    }
            }
        }
    }



    fun getCoinInfo(id : String = coinName) {
        coinName = id
        coinInfoScreenStates = CoinInfoScreenStates.Loading
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                coinInfoScreenStates =
                    try {
                        val coinInfo = itemSelectedUseCase.execute(coinName)
                        CoinInfoScreenStates.Success(coinInfo)
                    } catch (e: Exception) {
                        CoinInfoScreenStates.Error(refreshAction = { getCoinList() })
                    }
            }
        }
    }

    fun refreshCoinInfo(){

    }


}