package com.edcode.dogapi.di

import org.koin.dsl.module
import com.edcode.dogapi.data.DogApiRepo
import com.edcode.dogapi.domain.DogApiRepoImp
import com.edcode.dogapi.presentation.screens.DogApiViewModel
import org.koin.core.context.startKoin


val appModule = module{

   single<DogApiRepo> { DogApiRepoImp() }
   factory { DogApiViewModel(dogApiRepo = get()) }
}

fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}

