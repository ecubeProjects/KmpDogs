package com.edcode.dogapi.di

import org.koin.core.context.startKoin


val appModule = {

}

fun initializeKoin() {
    startKoin {
        modules()
    }
}

