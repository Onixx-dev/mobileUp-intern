package com.onixx.mobileupintern.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onixx.mobileupintern.domain.usecase.ItemSelectedUseCase
import com.onixx.mobileupintern.domain.usecase.UploadCoinsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrencyViewModel(
    private val uploadCoinsUseCase: UploadCoinsUseCase,
    private val itemSelectedUseCase: ItemSelectedUseCase
) : ViewModel() {

    private val uploadCount = 30
    private var currencyName = "usd"
    private val coinName = "bitcoin"

    fun getCoinList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val temp = uploadCoinsUseCase.execute(currencyName, uploadCount)
                Log.d("----list", temp.toString())
            }
        }
    }

    fun getCoinInfo(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val temp = itemSelectedUseCase.execute(coinName)
                Log.d("----info", temp.toString())
            }
        }
    }



}