package com.edcode.dogapi.di

import com.edcode.dogapi.data.DogApiRepo
import com.edcode.dogapi.domain.DogApiRepoImp
import com.edcode.dogapi.presentation.screens.DogApiViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.module


val appModule = module{

   single<DogApiRepo> { DogApiRepoImp() }
   factory { DogApiViewModel(dogApiRepo = get()) }

}



fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}


object NetworkUtils {
    val httpClient = HttpClient {
        install(ContentNegotiation){
            json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
        }
    }
}



