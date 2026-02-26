package com.edcode.dogapi.domain

import com.edcode.dogapi.data.DogApiRepo
import com.edcode.dogapi.di.NetworkUtils.httpClient
import com.edcode.dogapi.models.ApiDogResponse
import com.edcode.dogapi.models.ApiResponse
import com.edcode.dogapi.models.Hero
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class DogApiRepoImp: DogApiRepo {
    override fun getSuperheroList(
        superheroName: String,
        onSuccessResponse: (List<Hero>) -> Unit
    ) {
        if (superheroName.isBlank()) return
        val url = "https://www.superheroapi.com/api.php/79c99fda9894cf4017793cdb40721cb6/search/$superheroName"
        CoroutineScope(Dispatchers.IO).launch {
            val response = httpClient.get(url).body<ApiResponse>()
            onSuccessResponse(response.results)
         }
        }

    override fun getDogs(onSuccessResponse: (String) -> Unit) {
        val url = "https://dog.ceo/api/breeds/image/random"
        CoroutineScope(Dispatchers.IO).launch {
            val response = httpClient.get(url).body<ApiDogResponse>()
            onSuccessResponse(response.ok)
        }
    }
}