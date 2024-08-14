package com.onixx.mobileupintern.presentation.di

import com.onixx.mobileupintern.presentation.viewmodel.CurrencyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel<CurrencyViewModel> { CurrencyViewModel(get(), get()) }


}