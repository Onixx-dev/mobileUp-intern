package com.onixx.mobileupintern.presentation

import android.app.Application
import com.onixx.mobileupintern.presentation.di.dataModule
import com.onixx.mobileupintern.presentation.di.domainModule
import com.onixx.mobileupintern.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(){
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(
                presentationModule, dataModule, domainModule
            ))
        }

    }
}