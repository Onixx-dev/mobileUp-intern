package com.onixx.mobileupintern.presentation.di

import com.onixx.mobileupintern.domain.usecase.ItemSelectedUseCase
import com.onixx.mobileupintern.domain.usecase.UploadCoinsUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<UploadCoinsUseCase> { UploadCoinsUseCase(get()) }

    factory<ItemSelectedUseCase> { ItemSelectedUseCase(get()) }

}