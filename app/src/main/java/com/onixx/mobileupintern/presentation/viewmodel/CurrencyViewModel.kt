package com.onixx.mobileupintern.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onixx.mobileupintern.domain.usecase.GetCoinsListUseCase
import com.onixx.mobileupintern.domain.usecase.ItemSelectedUseCase
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


/**
 * основная viewModel. Подтягивает два юзКейса: загрузки списка данных и загрузки информации о конкретной валюте
 * Собирается через модули Koin, см. App
 * @see GetCoinsListUseCase
 * @see ItemSelectedUseCase
 * @see presentation.App
 */
class CurrencyViewModel(
    private val getCoinsListUseCase: GetCoinsListUseCase,
    private val itemSelectedUseCase: ItemSelectedUseCase
) : ViewModel() {


    /**
     * стейт-холдеры для экранов списка валют, информации, и обновления pull-to-refresh
     * @see CoinListScreenStates
     * @see CoinInfoScreenStates
     */
    var coinListScreenStates: CoinListScreenStates by mutableStateOf(CoinListScreenStates.Loading)
    var coinInfoScreenStates: CoinInfoScreenStates by mutableStateOf(CoinInfoScreenStates.Loading)
    private val _isLoading = MutableStateFlow(false)
    var isLoading = _isLoading.asStateFlow()


    /**
     * обьекты для работы с валютами исходными валютами
     * @param numberFormat - для преобразоведения строки с ценой к локализированному значению
     * @param currencyArray - для автоматической генерации чипсов-переключателей на экране списка.
     * чтобы добавить новое - добавить значение в массив, и указать желаемую локализацию в changeBaseCurrency
     * @see changeBaseCurrency
     */
    var numberFormat: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
    val currencyArray = arrayListOf("usd", "rub")

    private val uploadCount = 30
    private var currencyName: String by mutableStateOf(currencyArray.first())
    var coinName = "bitcoin"



    /**
     * смена базовой валюты, от которой расчитывается курс, и локали для её правильного отображения в списке
     * Вызывается при переключении валюты в topBar экрана списка валют. Изменяет валюту, локаль для её отображеиня и вызывает
     * запрос нового списка валют
     * @param newCurrency - один из элементов массива currencyArray
     * @see getCoinList
     */
    fun changeBaseCurrency(newCurrency: String) {
        currencyName = newCurrency
        numberFormat = when (newCurrency) {
            "usd" -> NumberFormat.getCurrencyInstance(Locale.US)
            "rub" -> NumberFormat.getCurrencyInstance(Locale("ru", "RU"))
            else -> NumberFormat.getCurrencyInstance(Locale.US)
        }
        getCoinList()
    }


    /**
     * Запрос котировок для экрана-списка валют. Устанавливаем состояние экрана = загрузка и запускаем корутину.
     * Результат пишем в Стейт-холдер, либо успех либо ошибку.
     * В случае успеха для каждой валюты расчитываем дополнительные поля с информацией, чтобы не пересчитывать их постоянно при рекомпозиции
     * @see Coin - дополнительные поля промаркированы
     * @see HostListScreen
     */
    fun getCoinList() {
        coinListScreenStates = CoinListScreenStates.Loading
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                coinListScreenStates =
                    try {
                        val list = getCoinsListUseCase.execute(currencyName, uploadCount)
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


    /**
     * Используется при pull-to-refresh списка котировок. Все то же самое, что и getCoinList,
     * только добавилась работа с состоянием pull-to-refresh
     * @see Coin - дополнительные поля промаркированы
     * @see getCoinList
     * @see HostListScreen
     */
    fun refreshCoinList() {
        _isLoading.value = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                coinListScreenStates =
                    try {
                        val list = getCoinsListUseCase.execute(currencyName, uploadCount)
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


    /**
     * получение подробных данных о конкретной валюте. Используется при событии клика по элементу списка
     * Выставляет состояние = загрузке, и запускает корутину с запросом информации. По окончанию выполнения
     * редирект на хост-экран информации с состоянием успеха или ошибки
     * @see HostInfoScreen
     */
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


}