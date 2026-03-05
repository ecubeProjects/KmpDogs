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


    /**
     * Le pasamos casos de uso al viewmodel*/

    factory { DogApiViewModel(dogApiUseCases = get()) }

    /**
    Insertamos casos de usos para las clases*/

   single { DogApiUseCases (repo = get())}
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


/**
Insertamos casos de usos , usamos suspend cuando consumimos o vamos a base de datos*/

 class DogApiUseCases(private val repo: DogApiRepo) {
     suspend fun getDogPic() = getDogPic(repo).invoke()
}



/**
 * Caso de uso de ejemplo*/


class getDogPic (private val repo: DogApiRepo)
{
    suspend operator fun invoke() = repo.getDogs()
}