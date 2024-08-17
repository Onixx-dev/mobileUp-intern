package com.onixx.mobileupintern.presentation.di

import com.onixx.mobileupintern.domain.usecase.GetCoinsListUseCase
import com.onixx.mobileupintern.domain.usecase.ItemSelectedUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetCoinsListUseCase> { GetCoinsListUseCase(get()) }

    factory<ItemSelectedUseCase> { ItemSelectedUseCase(get()) }

}