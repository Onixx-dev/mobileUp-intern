package com.onixx.mobileupintern.presentation.di

import com.onixx.mobileupintern.data.repositoryImpl.CoinInfoRepositoryImpl
import com.onixx.mobileupintern.data.repositoryImpl.CoinListRepositoryImpl
import com.onixx.mobileupintern.domain.repository.CoinInfoRepository
import com.onixx.mobileupintern.domain.repository.CoinListRepository
import org.koin.dsl.module

val dataModule = module {

    single<CoinListRepository> { CoinListRepositoryImpl() }

    single<CoinInfoRepository> { CoinInfoRepositoryImpl() }

}

