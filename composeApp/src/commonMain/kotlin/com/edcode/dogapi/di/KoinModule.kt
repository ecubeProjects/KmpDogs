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

    single <HttpClient> {
        HttpClient {
            install(ContentNegotiation)
            {
                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }
        }
    }

   single<DogApiRepo> { DogApiRepoImp(client = get()) }



    /**
     * Le pasamos casos de uso  en lugar del repositorio al viewmodel*/

    factory { DogApiViewModel(dogApiUseCases = get()) }

    /**
    Insertamos casos de usos para las clases , el singleton (data class) recibe los casos de uso y devuelve una clase */

    single { DogUseCases( GetSingleDogPics = get() , getBreads = get()) }


    single { GetSingleDogPics(repo = get())}
    single { getBreads(repo = get())}

}




fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}




/**
Insertamos casos de usos , usamos suspend cuando consumimos o vamos a base de datos*/

data class DogUseCases(
    val GetSingleDogPics: GetSingleDogPics,
    val getBreads: getBreads
)



/**
 * Caso de uso de ejemplo*/

  class GetSingleDogPics (private val repo: DogApiRepo) {
    suspend operator fun invoke() = repo.getDogs()
}

class getBreads (private val repo: DogApiRepo) {
    suspend operator fun invoke() = repo.getBreads()
}



